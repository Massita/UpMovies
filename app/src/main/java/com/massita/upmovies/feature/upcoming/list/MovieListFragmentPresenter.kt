package com.massita.upmovies.feature.upcoming.list

import com.massita.upmovies.api.ApiClient
import com.massita.upmovies.api.model.UpcomingList
import com.massita.upmovies.api.service.MovieService
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Response
import java.net.HttpURLConnection

class MovieListFragmentPresenter(private var view: MovieListFragmentContract.View?) : MovieListFragmentContract.Presenter {

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()
    private val movieService: MovieService = ApiClient().getMovieService()
    private var currentPage = 1

    override fun start() {
        view?.setupRecyclerView()
    }

    override fun nextPage() {
        view?.showLoading()

        val disposable = movieService
                .getUpcoming("***REMOVED***", "pt-BR", currentPage)
                .doOnNext(this::onNext)
                .subscribe()

        compositeDisposable.add(disposable)
    }

    private fun onNext(response: Response<UpcomingList>) {
        when (response.code()) {
            HttpURLConnection.HTTP_OK -> onRequestOk(response.body())
        }
    }

    private fun onRequestOk(list: UpcomingList?) {
        currentPage++

    }
}