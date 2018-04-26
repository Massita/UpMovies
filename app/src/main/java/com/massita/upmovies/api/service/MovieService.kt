package com.massita.upmovies.api.service

import com.massita.upmovies.api.model.UpcomingList
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("3/movie/upcoming")
    fun getUpcoming(
            @Query("api_key") apiKey: String,
            @Query("language") language: String,
            @Query("page") page: Int) : Observable<Response<UpcomingList>>

}