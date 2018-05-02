package com.massita.upmovies.feature.upcoming

import com.massita.upmovies.api.model.Movie

interface UpcomingActivityContract {

    interface View {

        fun showMovieListFragment()

        fun showMovieDetailFragment(movie: Movie)

        fun onMovieSelected(movie: Movie)

    }

    interface Presenter {

        fun start()

        fun onMovieSelected(movie: Movie)

    }

}