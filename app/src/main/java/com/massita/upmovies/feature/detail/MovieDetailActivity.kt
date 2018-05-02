package com.massita.upmovies.feature.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.massita.upmovies.R
import com.massita.upmovies.api.service.ServiceConfig
import com.massita.upmovies.extension.load
import kotlinx.android.synthetic.main.activity_movie_detail.*
import retrofit2.Callback
import java.lang.Exception

class MovieDetailActivity : AppCompatActivity(), MovieDetailActivityContract.View, com.squareup.picasso.Callback {
    override fun onSuccess() {
    }

    override fun onError(e: Exception?) {
    }

    companion object {

        fun newIntent(context: Context) : Intent {
            return Intent(context, MovieDetailActivity::class.java)
        }
    }

    lateinit var presenter: MovieDetailActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_movie_detail)

        setSupportActionBar(toolbar);
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        coverImage.load(ServiceConfig.IMAGE_BASE_URL + "/kbGO5mHPK7rh516MgAIJUQ9RvqD.jpg", this)

        presenter = MovieDetailActivityPresenter(this)
    }
}