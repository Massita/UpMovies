package com.massita.upmovies.feature.detail.fragment

interface MovieDetailFragmentContract {

    interface View {

        fun setupListeners()

        fun setMovieTitle(title: String?)

        fun setMovieOriginalTitle(originalTitle: String?, year: String?)

        fun setMovieOverview(overview: String?)

        fun setMovieCover(path: String?)

        fun setMovieGenres(genres: String)

        fun showTrailerButton()

        fun startTrailer(key: String)

    }

    interface Presenter {

        fun start()

        fun onPosterLoaded() : () -> Unit

        fun loadDetails()

        fun onTrailerClicked()

    }

}