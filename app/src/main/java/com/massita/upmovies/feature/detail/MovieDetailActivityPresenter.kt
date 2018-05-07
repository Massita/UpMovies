package com.massita.upmovies.feature.detail

import com.massita.upmovies.api.model.Movie
import com.massita.upmovies.api.service.ServiceConfig
import com.massita.upmovies.extension.getDaysAgo
import com.massita.upmovies.extension.isAfterToday
import com.massita.upmovies.extension.toLocalDateString

class MovieDetailActivityPresenter(var view: MovieDetailActivityContract.View?,
                                   var movie: Movie?) : MovieDetailActivityContract.Presenter {

    override fun start() {
        view?.setupCollapsingToolbar()
        view?.setCoverImage(ServiceConfig.IMAGE_BASE_URL + movie?.backdropPath)
        view?.showDetailFragment(movie?.id ?: 0)
    }

    override fun destroy() {
        view = null
    }

    override fun applyPalette() : () -> Unit = {
        view?.setupCollapsingToolbarColors()
    }

    override fun onRememberClick() {
        val options = getRememberOptions()
        if(options.isNotEmpty()) {
            view?.showRememberDialogPicker(options)
        } else {
            view?.showEmptyRememberDatesMessage()
        }
    }

    private fun getRememberOptions() : Array<CharSequence> {
        var options : Array<CharSequence> = arrayOf()

        val date = movie?.releaseDate
        val dateBefore = date?.getDaysAgo(1)
        val dateWeekBefore = date?.getDaysAgo(7)

        if(date != null && date.isAfterToday()) {
            options = options.plus(date.toLocalDateString())
        }

        if(dateBefore != null && dateBefore.isAfterToday()) {
            options = options.plus(dateBefore.toLocalDateString() ?: "")
        }

        if(dateWeekBefore != null && dateWeekBefore.isAfterToday()) {
            options = options.plus(dateWeekBefore.toLocalDateString() ?: "")
        }

        return options
    }

}