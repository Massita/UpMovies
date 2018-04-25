package com.massita.upmovies.feature.upcoming

class UpcomingActivityPresenter(private var view: UpcomingActivityContract.View?) : UpcomingActivityContract.Presenter {

    override fun start() {
        view?.showMovieListFragment()
    }

}