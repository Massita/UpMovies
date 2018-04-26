package com.massita.upmovies.api.service

import com.massita.upmovies.api.model.UpcomingList
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieService {

    @GET("3/movie/upcoming")
    fun getUpcoming() : Observable<UpcomingList>

}