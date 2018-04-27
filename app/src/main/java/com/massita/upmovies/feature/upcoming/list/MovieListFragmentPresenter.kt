package com.massita.upmovies.feature.upcoming.list

import com.massita.upmovies.api.ApiClient
import com.massita.upmovies.api.model.UpcomingList
import com.massita.upmovies.api.service.MovieService
import com.massita.upmovies.api.service.ServiceConfig
import com.massita.upmovies.extension.getDefaultIsoString
import com.massita.upmovies.feature.upcoming.list.adapter.MovieListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.net.HttpURLConnection
import java.util.*

class MovieListFragmentPresenter(private var view: MovieListFragmentContract.View?) : MovieListFragmentContract.Presenter {

    private val compositeDisposable : CompositeDisposable = CompositeDisposable()
    private val movieService: MovieService = ApiClient().getMovieService()
    private var currentPage = 1
    private var adapter: MovieListAdapter = MovieListAdapter(mutableListOf()) { onMovieSelected(it) }

    override fun start() {
        view?.setupRecyclerView()
        view?.setupSwipeToRefresh()
        view?.setAdapter(adapter)
    }

    override fun refreshSearch() {
        val disposable = movieService
                .getUpcoming(ServiceConfig.API_KEY, Locale.getDefault().getDefaultIsoString(), 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onRefreshSuccess(it) } ,
                        { onRequestError(it) }
                )

        compositeDisposable.add(disposable)
    }

    override fun nextPage() {
        view?.showLoading()

        val disposable = movieService
                .getUpcoming(ServiceConfig.API_KEY, Locale.getDefault().getDefaultIsoString(), currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onNextPageSuccess(it) } ,
                        { onRequestError(it) }
                )

        compositeDisposable.add(disposable)
    }

    private fun onNextPageSuccess(response: Response<UpcomingList>) {
        when (response.code()) {
            HttpURLConnection.HTTP_OK -> onRequestOk(response.body())
        }
    }

    private fun onRequestOk(list: UpcomingList?) {
        currentPage++
        adapter.addAll(list!!.results)
        adapter.notifyDataSetChanged()

        if(adapter.itemCount == 0) {
            view?.showEmptyPlaceholder()
        }

        view?.hideLoading()
    }

    private fun onRequestError(error: Throwable) {
        view?.hideLoading()
        view?.hideRefresh()

        if(adapter.itemCount == 0) {
            view?.showErrorMessagePlaceholder()
        }
    }

    private fun onRefreshSuccess(response: Response<UpcomingList>) {
        when (response.code()) {
            HttpURLConnection.HTTP_OK -> onRefresh(response.body())
        }
    }

    private fun onRefresh(list: UpcomingList?) {
        currentPage = 1
        view?.hidePlaceholder()
        adapter.removeAll()
        adapter.addAll(list!!.results)
        adapter.notifyDataSetChanged()

        if(adapter.itemCount == 0) {
            view?.showEmptyPlaceholder()
        }

        view?.hideRefresh()
    }

    private fun onMovieSelected(position: Int) {

    }
}