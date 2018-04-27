package com.massita.upmovies.api

import com.massita.upmovies.api.service.MovieService
import com.massita.upmovies.api.service.ServiceConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val retrofit = Retrofit.Builder()
            .baseUrl(ServiceConfig.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun getMovieService() : MovieService {
        return retrofit.create(MovieService::class.java)
    }

}