package com.massita.upmovies.api.model

import com.google.gson.annotations.SerializedName

data class UpcomingList(
        @SerializedName("page") val page: Int,
        @SerializedName("results") val results: List<Movie>,
        @SerializedName("dates") val dates: DateRange,
        @SerializedName("total_pages") val totalPages: Int,
        @SerializedName("total_results") val totalResults: Int
)

data class DateRange(
        @SerializedName("maximum") val maximum: String,
        @SerializedName("minimum") val minimum: String
)