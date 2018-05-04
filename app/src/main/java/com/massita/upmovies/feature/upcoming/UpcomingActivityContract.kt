package com.massita.upmovies.feature.upcoming

interface UpcomingActivityContract {

    interface View {

        fun showMovieListFragment()

    }

    interface Presenter {

        fun start()

    }

}