package com.massita.upmovies.feature.detail.fragment

interface MovieDetailFragmentContract {

    interface View {

        fun setMovieTitle(title: String?)

        fun setMovieTagline(tagline: String?)

        fun setMovieOriginalTitle(originalTitle: String?)

        fun setMovieOverview(overview: String?)

        fun setMovieCover(path: String?)

    }

    interface Presenter {

        fun start()

        fun onPosterLoaded() : () -> Unit

        fun loadDetails()

    }

}