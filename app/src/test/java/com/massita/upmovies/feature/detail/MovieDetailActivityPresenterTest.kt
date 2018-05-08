package com.massita.upmovies.feature.detail

import com.massita.upmovies.api.model.Movie
import com.massita.upmovies.api.service.ServiceConfig
import com.massita.upmovies.extension.getDaysAgo
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class MovieDetailActivityPresenterTest {
    lateinit var presenter: MovieDetailActivityContract.Presenter
    private var view: MovieDetailActivityContract.View? = null
    private var movie: Movie? = null

    @Before
    fun setUp() {
        view = Mockito.mock(MovieDetailActivityContract.View::class.java)
        movie = Movie("overview", Date(), 1, "originalTitle", "pt_BR", "title", "backdropPath")

        presenter = MovieDetailActivityPresenter(view, movie)
    }

    @Test
    fun `when_start_should_setupCollapsingToolbar_setCoverImage_showDetailFragment`() {
        presenter.start()

        Mockito.verify(view)?.setupCollapsingToolbar()
        Mockito.verify(view)?.setCoverImage(ServiceConfig.IMAGE_BASE_URL + movie?.backdropPath)
        Mockito.verify(view)?.showDetailFragment(movie?.id ?: 0)
    }

    @Test
    fun `when_onRememberClick_with_DateNow_should_showEmptyRememberDatesMessage`() {
        presenter.onRememberClick()

        Mockito.verify(view)?.showEmptyRememberDatesMessage()
    }

    @Test
    fun `when_onRememberDateSelected0_should_scheduleNotification`() {
        presenter.onRememberDateSelected(0)

        Mockito.verify(view)?.scheduleNotification(movie?.releaseDate?.time!!, movie!!)
    }

    @Test
    fun `when_onRememberDateSelected1_should_scheduleNotification`() {
        presenter.onRememberDateSelected(1)

        Mockito.verify(view)?.scheduleNotification(movie?.releaseDate?.getDaysAgo(1)?.time!!, movie!!)
    }

    @Test
    fun `when_onRememberDateSelected2_should_scheduleNotification`() {
        presenter.onRememberDateSelected(2)

        Mockito.verify(view)?.scheduleNotification(movie?.releaseDate?.getDaysAgo(7)?.time!!, movie!!)
    }
}