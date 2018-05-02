package com.massita.upmovies.feature.detail

class MovieDetailActivityPresenter(var view: MovieDetailActivityContract.View?) : MovieDetailActivityContract.Presenter {

    override fun start() {
        view?.showDetailFragment()
    }

}