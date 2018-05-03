package com.massita.upmovies.feature.upcoming

import com.massita.upmovies.api.model.Movie

class UpcomingActivityPresenter(private var view: UpcomingActivityContract.View?) : UpcomingActivityContract.Presenter {

    object Tag {
        const val MOVIE_LIST_FRAGMENT = "MOVIE_LIST_FRAGMENT"
    }

    override fun start() {
        view?.showMovieListFragment()
    }

}