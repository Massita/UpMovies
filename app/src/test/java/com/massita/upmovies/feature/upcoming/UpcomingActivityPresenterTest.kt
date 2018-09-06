package com.massita.upmovies.feature.upcoming

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

class UpcomingActivityPresenterTest {
    lateinit var presenter: UpcomingActivityContract.Presenter
    private var view: UpcomingActivityContract.View? = null

    @Before
    fun setUp() {
        view = Mockito.mock(UpcomingActivityContract.View::class.java)
        presenter = UpcomingActivityPresenter(view)
    }

    @Test
    fun `when_start_should_showMovieListFragment`() {
        presenter.start()

        Mockito.verify(view)?.showMovieListFragment()
    }
}