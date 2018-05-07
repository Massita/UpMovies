package com.massita.upmovies.feature.upcoming.list.fragment

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.massita.upmovies.R
import com.massita.upmovies.api.ApiClient
import com.massita.upmovies.api.model.Movie
import com.massita.upmovies.feature.detail.MovieDetailActivity
import com.massita.upmovies.feature.upcoming.list.adapter.MovieListAdapter
import com.massita.upmovies.feature.upcoming.list.listener.InfiniteScrollListener
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

        presenter = MovieListFragmentPresenter(this, ApiClient(context!!).getMovieService())
        presenter.start()

        presenter.nextPage()
    }

    override fun onDestroyView() {
        presenter.destroy()
        super.onDestroyView()
    }

    override fun setupRecyclerView() {
        val linearLayout = LinearLayoutManager(context, LinearLayout.VERTICAL, false)
        recyclerViewMovieList.layoutManager = linearLayout
        recyclerViewMovieList.addOnScrollListener(InfiniteScrollListener( { presenter.nextPage() } , linearLayout))
    }

    override fun setupSwipeToRefresh() {
        swipeToRefresh.setOnRefreshListener { presenter.refreshSearch() }
    }

    override fun hideRefresh() {
        swipeToRefresh.isRefreshing = false
    }

    override fun setAdapter(adapter: MovieListAdapter) {
        recyclerViewMovieList.adapter = adapter
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
    }

    override fun showEmptyPlaceholder() {
        imageError.setImageResource(R.drawable.ic_theaters_black_120dp)
        textError.setText(R.string.upcoming_fetch_empty)
        group.visibility = View.VISIBLE
    }

    override fun showErrorMessagePlaceholder() {
        imageError.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_120dp)
        textError.setText(R.string.upcoming_fetch_error)
        group.visibility = View.VISIBLE
    }

    override fun hidePlaceholder() {
        group.visibility = View.GONE
    }

    override fun onMovieSelected(movie: Movie, view: View) {
        val intent = MovieDetailActivity.newIntent(context!!, movie)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            val p1 = Pair.create(view, getString(R.string.transition_movie_image))
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity as Activity, p1)
            startActivity(intent, options.toBundle())
        } else {
            startActivity(intent)
        }
    }

}