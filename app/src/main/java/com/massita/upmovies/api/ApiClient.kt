package com.massita.upmovies.api

import android.content.Context
import com.massita.upmovies.api.service.MovieService
import com.massita.upmovies.api.service.ServiceConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.xml.datatype.DatatypeConstants.DAYS
import okhttp3.CacheControl
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS
import android.net.NetworkInfo





class ApiClient(var context: Context) {

    companion object {
        const val HEADER_CACHE_CONTROL = "Cache-Control"
        const val HEADER_PRAGMA = "Pragma"
    }

    private val retrofit by lazy {
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(provideOfflineCacheInterceptor())
                .addNetworkInterceptor(provideInterceptorCache())
                .cache(provideCache())

        Retrofit.Builder()
                .baseUrl(ServiceConfig.BASE_URL)
                .client(okHttpClient.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }



    fun getMovieService() : MovieService {
        return retrofit.create(MovieService::class.java)
    }

    fun provideCache() : Cache {
        return Cache(File(context.cacheDir, "http-cache"), 10 * 1024 * 1024) // 10mb of cache
    }

    fun provideInterceptorCache() : Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl: CacheControl

            if (isConnected()) {
                cacheControl = CacheControl.Builder()
                        .maxAge(0, TimeUnit.SECONDS)
                        .build()
            } else {
                cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()
            }

            response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
        }
    }

    private fun provideOfflineCacheInterceptor(): Interceptor {
        return Interceptor{ chain ->
            var request = chain.request()

            if (!isConnected()) {
                val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()

                request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
            }

            chain.proceed(request)
        }
    }

    fun isConnected(): Boolean {
        val e = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as android.net.ConnectivityManager
        val activeNetwork = e.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}