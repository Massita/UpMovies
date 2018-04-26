package com.massita.upmovies.feature.upcoming.list

interface MovieListFragmentContract {
    interface View {

        fun showLoading()

        fun hideLoading()

        fun setupRecyclerView()

    }

    interface Presenter {

        fun start()

    }
}