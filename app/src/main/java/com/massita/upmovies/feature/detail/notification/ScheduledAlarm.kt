package com.massita.upmovies.feature.detail.notification

import com.google.gson.annotations.SerializedName
import com.massita.upmovies.api.model.Movie

data class ScheduledAlarm(
        @SerializedName("id") val id: Int,
        @SerializedName("time") val time: Long,
        @SerializedName("movie") val movie: Movie
)