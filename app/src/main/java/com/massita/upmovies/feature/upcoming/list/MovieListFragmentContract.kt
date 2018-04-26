package com.massita.upmovies.feature.upcoming.list

import com.massita.upmovies.feature.upcoming.list.adapter.MovieListAdapter

interface MovieListFragmentContract {
    interface View {

        fun showLoading()

        fun hideLoading()

        fun setupRecyclerView()

        fun setAdapter(adapter: MovieListAdapter)

    }

    interface Presenter {

        fun start()

        fun nextPage()

    }
}