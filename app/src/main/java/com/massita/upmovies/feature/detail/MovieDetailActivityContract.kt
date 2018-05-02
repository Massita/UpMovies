package com.massita.upmovies.feature.detail

interface MovieDetailActivityContract {

    interface View {

        fun showDetailFragment()

    }

    interface Presenter {

        fun start()

    }

}