package com.massita.upmovies.feature.upcoming.list

import com.massita.upmovies.api.ApiClient
import com.massita.upmovies.api.model.Movie
import com.massita.upmovies.api.model.UpcomingList
import com.massita.upmovies.api.service.MovieService
import com.massita.upmovies.feature.upcoming.list.adapter.MovieListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.net.HttpURLConnection

class MovieListFragmentPresenter(private var view: MovieListFragmentContract.View?) : MovieListFragmentContract.Presenter {

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()
    private val movieService: MovieService = ApiClient().getMovieService()
    private var currentPage = 1
    private var adapter: MovieListAdapter = MovieListAdapter(mutableListOf()) { onMovieSelected(it) }

    override fun start() {
        view?.setupRecyclerView()
        view?.setAdapter(adapter)
    }

    override fun nextPage() {
        view?.showLoading()

        val disposable = movieService
                .getUpcoming("***REMOVED***", "pt-BR", currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::onNext)
                .doOnComplete { view?.hideLoading() }
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
        adapter.addAll(list!!.results)
        adapter.notifyDataSetChanged()
    }

    private fun onMovieSelected(position: Int) {

    }
}