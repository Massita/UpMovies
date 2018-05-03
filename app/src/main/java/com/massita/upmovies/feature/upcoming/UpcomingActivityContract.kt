package com.massita.upmovies.feature.upcoming

import com.massita.upmovies.api.model.Movie

interface UpcomingActivityContract {

    interface View {

        fun showMovieListFragment()

    }

    interface Presenter {

        fun start()

    }

}