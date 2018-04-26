package com.massita.upmovies.feature.upcoming.list

class MovieListFragmentPresenter(private var view: MovieListFragmentContract.View?) : MovieListFragmentContract.Presenter {

    override fun start() {
        view?.setupRecyclerView()
    }

}