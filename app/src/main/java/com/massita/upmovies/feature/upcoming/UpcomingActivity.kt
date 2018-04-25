package com.massita.upmovies.feature.upcoming

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class UpcomingActivity : AppCompatActivity(), UpcomingActivityContract.View {
    private lateinit var presenter: UpcomingActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = UpcomingActivityPresenter(this)
    }

    override fun showMovieListFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMovieDetailFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}