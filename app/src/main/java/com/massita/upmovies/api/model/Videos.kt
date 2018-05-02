package com.massita.upmovies.api.model

import com.google.gson.annotations.SerializedName

data class Videos(
        @SerializedName("id") val id: Int,
        @SerializedName("results") val videos: List<Video>
)