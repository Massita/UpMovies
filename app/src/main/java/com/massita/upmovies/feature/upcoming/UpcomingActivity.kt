package com.massita.upmovies.feature.upcoming

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.massita.upmovies.R
import com.massita.upmovies.feature.upcoming.list.MovieListFragment


class UpcomingActivity : AppCompatActivity(), UpcomingActivityContract.View {
    private lateinit var presenter: UpcomingActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming)

        presenter = UpcomingActivityPresenter(this)
        presenter.start()
    }

    override fun showMovieListFragment() {
        val fragment = MovieListFragment.newInstance()
        showFragment(fragment, UpcomingActivityPresenter.Tag.MOVIE_LIST_FRAGMENT)
    }

    override fun showMovieDetailFragment() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.addToBackStack(tag)
        transaction.replace(R.id.fragment_container, fragment, tag)
        transaction.commit()
    }
}