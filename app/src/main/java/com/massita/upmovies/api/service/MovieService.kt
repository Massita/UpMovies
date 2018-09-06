package com.massita.upmovies.api.service

import com.massita.upmovies.api.model.MovieDetail
import com.massita.upmovies.api.model.UpcomingList
import com.massita.upmovies.api.model.Videos
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/upcoming")
    fun getUpcoming(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int) : Observable<Response<UpcomingList>>

    @GET("3/movie/{movieId}")
    fun getDetails(
            @Path("movieId") movieId: Int,
            @Query("api_key") apiKey: String,
            @Query("language") language: String) : Observable<Response<MovieDetail>>

    @GET("3/movie/{movieId}/videos")
    fun getVideos(
            @Path("movieId") movieId: Int,
            @Query("api_key") apiKey: String,
            @Query("language") language: String) : Observable<Response<Videos>>
}