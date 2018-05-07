package com.massita.upmovies.feature.detail

interface MovieDetailActivityContract {

    interface View {

        fun showDetailFragment(movieId: Int)

        fun setCoverImage(path: String)

        fun setupCollapsingToolbar()

        fun setupCollapsingToolbarColors()

        fun showRememberDialogPicker(options: Array<CharSequence>)

        fun showEmptyRememberDatesMessage()

    }

    interface Presenter {

        fun start()

        fun destroy()

        fun applyPalette() : () -> Unit

        fun onRememberClick()

    }

}