package com.massita.upmovies.feature.detail

class MovieDetailActivityPresenter(var view: MovieDetailActivityContract.View?) : MovieDetailActivityContract.Presenter {

    override fun start() {
        view?.setupCollapsingToolbar()
        view?.showDetailFragment()
    }

    override fun destroy() {
        view = null
    }

    override fun applyPalette() : () -> Unit = {
        view?.setupCollapsingToolbarColors()
    }

}