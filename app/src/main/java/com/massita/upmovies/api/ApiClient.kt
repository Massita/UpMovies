package com.massita.upmovies.api

import com.massita.upmovies.api.service.MovieService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org"
    }

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getMovieService() : MovieService {
        return retrofit.create(MovieService::class.java)
    }

}