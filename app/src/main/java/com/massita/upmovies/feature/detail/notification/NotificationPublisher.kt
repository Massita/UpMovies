package com.massita.upmovies.feature.detail.notification

import android.app.Notification
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.massita.upmovies.helper.NotificationHelper


class NotificationPublisher : BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_ID = "notification-id"
        const val NOTIFICATION = "notification"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val notification : Notification? = intent?.getParcelableExtra(NOTIFICATION)
        val notificationId = intent?.getIntExtra(NOTIFICATION_ID, 0) ?: 0
        val notificationHelper = NotificationHelper(context!!)

        notificationHelper.notify(notificationId, notification)
    }

}