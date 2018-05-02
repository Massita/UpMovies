package com.massita.upmovies.feature.detail.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.massita.upmovies.R

class MovieDetailFragment : Fragment(), MovieDetailFragmentContract.View {

    companion object {

        fun newInstance() : MovieDetailFragment {
            return MovieDetailFragment()
        }
    }

    private lateinit var presenter: MovieDetailFragmentContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MovieDetailFragmentPresenter(this)

        presenter.start()
    }
}