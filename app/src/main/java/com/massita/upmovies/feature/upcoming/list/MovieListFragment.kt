package com.massita.upmovies.feature.upcoming.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.massita.upmovies.R
import com.massita.upmovies.feature.upcoming.list.adapter.MovieListAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment(), MovieListFragmentContract.View {

    private lateinit var presenter: MovieListFragmentContract.Presenter

    companion object {

        fun newInstance() : MovieListFragment {
            return MovieListFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MovieListFragmentPresenter(this)
        presenter.start()

        presenter.nextPage()
    }

    override fun setupRecyclerView() {
        recyclerViewMovieList.layoutManager = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
    }

    override fun setAdapter(adapter: MovieListAdapter) {
        recyclerViewMovieList.adapter = adapter
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

}