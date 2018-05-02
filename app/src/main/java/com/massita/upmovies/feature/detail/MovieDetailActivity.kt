package com.massita.upmovies.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.massita.upmovies.R
import com.massita.upmovies.api.model.Movie
import com.massita.upmovies.api.service.ServiceConfig
import com.massita.upmovies.extension.load
import kotlinx.android.synthetic.main.activity_movie_detail.*
import java.lang.Exception

class MovieDetailActivity : AppCompatActivity(), MovieDetailActivityContract.View, com.squareup.picasso.Callback {
    private var movie: Movie? = null

    companion object {

        const val TAG_MOVIE = "TAG_MOVIE"

        fun newIntent(context: Context, movie: Movie) : Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(TAG_MOVIE, movie)
            return intent
        }
    }

    lateinit var presenter: MovieDetailActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_detail)

        setSupportActionBar(toolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        movie = intent.getParcelableExtra(TAG_MOVIE)

        coverImage.load(ServiceConfig.IMAGE_BASE_URL + movie?.backdropPath, {})
        collapsingToolbar.title = movie?.title

        presenter = MovieDetailActivityPresenter(this)
    }

    override fun onSuccess() {
    }

    override fun onError(e: Exception?) {
    }
}