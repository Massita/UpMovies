package com.massita.upmovies.api.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieDetail(
        @SerializedName("genres") val genres: List<Genre>,
        @SerializedName("homepage") val homepage: String,
        @SerializedName("id") val id: Int,
        @SerializedName("original_title") val originalTitle: String,
        @SerializedName("original_language") val originalLanguage: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("release_date") val releaseDate: Date,
        @SerializedName("title") val title: String,
        @SerializedName("video") val video: Boolean,
        @SerializedName("tagline") val tagline: String,
        @SerializedName("vote_average") val voteAverage: Float

)