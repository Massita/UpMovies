package com.massita.upmovies.helper

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.support.v4.app.NotificationCompat
import com.massita.upmovies.R


class NotificationHelper(context: Context) : ContextWrapper(context) {
    private var manager: NotificationManager? = null

    companion object {
        const val PRIMARY_CHANNEL = "default"
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(PRIMARY_CHANNEL,
                    getString(R.string.notification_channel_default), NotificationManager.IMPORTANCE_DEFAULT)
            channel.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            getManager()?.createNotificationChannel(channel)
        }
    }

    fun getNotification(title: String, body: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, PRIMARY_CHANNEL)
                .setContentTitle(title)
                .setStyle(NotificationCompat.BigTextStyle().bigText(body))
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setAutoCancel(true)
    }

    fun notify(id: Int, notification: Notification?) {
        getManager()?.notify(id, notification)
    }

    private fun getSmallIcon(): Int {
        return R.mipmap.ic_launcher
    }

    private fun getManager(): NotificationManager? {
        if (manager == null) {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return manager
    }
}