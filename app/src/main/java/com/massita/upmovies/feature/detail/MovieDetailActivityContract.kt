package com.massita.upmovies.feature.detail

interface MovieDetailActivityContract {

    interface View {

        fun showDetailFragment()

        fun setupCollapsingToolbar()

        fun setupCollapsingToolbarColors()

    }

    interface Presenter {

        fun start()

        fun applyPalette() : () -> Unit
    }

}