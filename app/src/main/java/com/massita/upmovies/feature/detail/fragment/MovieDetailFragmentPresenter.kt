package com.massita.upmovies.feature.detail.fragment

import com.massita.upmovies.api.ApiClient
import com.massita.upmovies.api.model.MovieDetail
import com.massita.upmovies.api.service.MovieService
import com.massita.upmovies.api.service.ServiceConfig
import com.massita.upmovies.extension.getDefaultIsoString
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.net.HttpURLConnection
import java.util.*

class MovieDetailFragmentPresenter(var view: MovieDetailFragmentContract.View?,
                                   private var movieId: Int) : MovieDetailFragmentContract.Presenter {
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()
    private val movieService: MovieService = ApiClient().getMovieService()
    private var movieDetail: MovieDetail? = null

    override fun start() {
        loadDetails()
    }

    override fun onPosterLoaded(): () -> Unit = {

    }

    override fun loadDetails() {
        val disposable = movieService
                .getDetails(movieId, ServiceConfig.API_KEY, Locale.getDefault().getDefaultIsoString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onLoadDetails(it) } ,
                        { onRequestError(it) }
                )

        compositeDisposable.add(disposable)
    }

    private fun onLoadDetails(response: Response<MovieDetail>) {
        when (response.code()) {
            HttpURLConnection.HTTP_OK -> onMovieDetailOk(response.body())
        }
    }

    private fun onMovieDetailOk(detail: MovieDetail?) {
        movieDetail = detail

        view?.setMovieOriginalTitle(movieDetail?.originalTitle)
        view?.setMovieOverview(movieDetail?.overview)
        view?.setMovieTitle(movieDetail?.title)
        view?.setMovieCover(ServiceConfig.IMAGE_BASE_URL + movieDetail?.posterPath)
    }

    private fun onRequestError(error: Throwable) {

    }
}