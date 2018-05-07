package com.massita.upmovies.feature.detail

import com.massita.upmovies.api.model.Movie

interface MovieDetailActivityContract {

    interface View {

        fun showDetailFragment(movieId: Int)

        fun setCoverImage(path: String)

        fun setupCollapsingToolbar()

        fun setupCollapsingToolbarColors()

        fun showRememberDialogPicker(options: Array<CharSequence>)

        fun showEmptyRememberDatesMessage()

        fun scheduleNotification(dateTimeInMillis: Int, title: String?, date: String?)

    }

    interface Presenter {

        fun start()

        fun destroy()

        fun applyPalette() : () -> Unit

        fun onRememberClick()

        fun onRememberDateSelected(selected: Int)

        fun getMovie() : Movie

    }

}