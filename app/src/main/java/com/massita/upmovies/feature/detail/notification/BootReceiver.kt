package com.massita.upmovies.feature.detail.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.massita.upmovies.R
import com.massita.upmovies.extension.toLocalDateString
import com.massita.upmovies.feature.detail.MovieDetailActivity
import com.massita.upmovies.helper.NotificationHelper
import com.massita.upmovies.helper.SharedPreferencesHelper

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action) {
            Intent.ACTION_BOOT_COMPLETED -> setAlarm(context)
        }
    }

    private fun setAlarm(context: Context?) {
        val alarms = SharedPreferencesHelper(context!!).getScheduledAlarms()
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        for(alarm in alarms) {
            val builder = NotificationHelper(context).getNotification(
                    context.getString(R.string.notification_movie_release_title),
                    context.getString(R.string.notification_movie_release_message, alarm.movie.title, alarm.movie.releaseDate.toLocalDateString()))

            val intent = MovieDetailActivity.newIntent(context, alarm.movie)
            val activity = PendingIntent.getActivity(context, alarm.id, intent, PendingIntent.FLAG_CANCEL_CURRENT)
            builder.setContentIntent(activity)

            val notification = builder.build()
            val notificationIntent = Intent(context, NotificationPublisher::class.java)
            notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, alarm.id)
            notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification)
            val pendingIntent = PendingIntent.getBroadcast(context, alarm.id, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT)


            alarmManager.set(AlarmManager.RTC_WAKEUP, alarm.time, pendingIntent)
        }
    }

}