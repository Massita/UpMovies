package com.massita.upmovies.feature.detail

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.view.MenuItem
import com.massita.upmovies.R
import com.massita.upmovies.api.model.Movie
import com.massita.upmovies.api.service.ServiceConfig
import com.massita.upmovies.extension.load
import com.massita.upmovies.feature.detail.fragment.MovieDetailFragment
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : AppCompatActivity(), MovieDetailActivityContract.View {
    private var movie: Movie? = null

    object Tag {
        const val MOVIE_DETAIL = "MOVIE_DETAIL"
    }

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
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        movie = intent.getParcelableExtra(TAG_MOVIE)

        presenter = MovieDetailActivityPresenter(this)

        coverImage.load(ServiceConfig.IMAGE_BASE_URL + movie?.backdropPath, presenter.applyPalette())

        presenter.start()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun setupCollapsingToolbar() {
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(this, android.R.color.transparent))
    }

    override fun setupCollapsingToolbarColors() {
        val bitmap = (coverImage.drawable as BitmapDrawable).bitmap
        Palette.from(bitmap).generate { palette ->
            val primary = ContextCompat.getColor(this, R.color.colorPrimary)

            collapsingToolbar.setContentScrimColor(palette.getVibrantColor(primary));
        }
    }

    override fun showDetailFragment() {
        val fragment = MovieDetailFragment.newInstance(movie?.id ?: 0)
        showFragment(fragment, Tag.MOVIE_DETAIL)
    }

    override fun onBackPressed() {
        finish()
    }

    fun showFragment(fragment: Fragment, tag: String) {
        val transaction = supportFragmentManager.beginTransaction()

        transaction.addToBackStack(tag)
        transaction.replace(R.id.fragment_container, fragment, tag)
        transaction.commit()
    }
}