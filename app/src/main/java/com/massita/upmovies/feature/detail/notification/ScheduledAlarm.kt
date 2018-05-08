package com.massita.upmovies.feature.detail.notification

import android.app.Notification
import com.google.gson.annotations.SerializedName

data class ScheduledAlarm(
        @SerializedName("id") val id: Int,
        @SerializedName("notification") val notification: Notification
)