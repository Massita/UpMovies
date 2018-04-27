package com.massita.upmovies.feature.upcoming.list

import com.massita.upmovies.feature.upcoming.list.adapter.MovieListAdapter

interface MovieListFragmentContract {
    interface View {

        fun showLoading()

        fun hideLoading()

        fun setupRecyclerView()

        fun setupSwipeToRefresh()

        fun hideRefresh()

        fun setAdapter(adapter: MovieListAdapter)

        fun showErrorMessagePlaceholder()

        fun showEmptyPlaceholder()

        fun hidePlaceholder()

    }

    interface Presenter {

        fun start()

        fun nextPage()

        fun refreshSearch()
    }
}