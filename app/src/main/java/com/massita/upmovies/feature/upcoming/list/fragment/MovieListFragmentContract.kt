package com.massita.upmovies.feature.upcoming.list.fragment

import com.massita.upmovies.api.model.Movie
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

        fun onMovieSelected(movie: Movie)

    }

    interface Presenter {

        fun start()

        fun nextPage()

        fun refreshSearch()
    }
}