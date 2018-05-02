package com.massita.upmovies.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        @SerializedName("overview") val overview: String,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("id") val id: Int,
        @SerializedName("original_title") val originalTitle: String,
        @SerializedName("original_language") val originalLanguage: String,
        @SerializedName("title") val title: String,
        @SerializedName("backdrop_path") val backdropPath: String?
) : Parcelable