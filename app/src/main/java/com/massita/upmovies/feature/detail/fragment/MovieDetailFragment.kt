package com.massita.upmovies.feature.detail.fragment

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.massita.upmovies.R
import com.massita.upmovies.api.ApiClient
import com.massita.upmovies.extension.load
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : Fragment(), MovieDetailFragmentContract.View {

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"

        fun newInstance(movieId: Int) : MovieDetailFragment {
            val fragment = MovieDetailFragment()
            val args = Bundle()

            args.putInt(EXTRA_MOVIE_ID, movieId)
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var presenter: MovieDetailFragmentContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = arguments?.getInt(EXTRA_MOVIE_ID, 0) ?: 0

        presenter = MovieDetailFragmentPresenter(this, ApiClient(context!!).getMovieService(), movieId)

        presenter.start()

        presenter.loadDetails()
    }

    override fun setupListeners() {
        buttonTrailer.setOnClickListener { presenter?.onTrailerClicked() }
    }

    override fun setMovieTitle(title: String?) {
        textMovieTitle.text = title
    }

    override fun setMovieOriginalTitle(originalTitle: String?, year: String?) {
        textMovieOriginalTitle.text = getString(R.string.movie_details_original_name, originalTitle, year)
    }

    override fun setMovieOverview(overview: String?) {
        textMovieOverview.text = overview
    }

    override fun setMovieCover(path: String?) {
        posterImage.load(path, presenter.onPosterLoaded())
    }

    override fun setMovieGenres(genres: String) {
        textMovieGenres.text = genres
    }

    override fun showTrailerButton() {
        buttonTrailer.visibility = View.VISIBLE
    }

    override fun startTrailer(key: String) {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$key"))
        val webIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=$key"))
        try {
            context?.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            context?.startActivity(webIntent)
        }

    }
}