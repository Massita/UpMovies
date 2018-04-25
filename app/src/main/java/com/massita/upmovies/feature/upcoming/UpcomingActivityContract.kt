package com.massita.upmovies.feature.upcoming

interface UpcomingActivityContract {

    interface View {

        fun showMovieListFragment()

        fun showMovieDetailFragment()

    }

    interface Presenter {

        fun start()

    }

}