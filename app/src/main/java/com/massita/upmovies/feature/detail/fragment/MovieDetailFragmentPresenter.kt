package com.massita.upmovies.feature.detail.fragment

import android.text.TextUtils
import com.massita.upmovies.api.ApiClient
import com.massita.upmovies.api.model.MovieDetail
import com.massita.upmovies.api.model.Video
import com.massita.upmovies.api.model.Videos
import com.massita.upmovies.api.service.MovieService
import com.massita.upmovies.api.service.ServiceConfig
import com.massita.upmovies.extension.getDefaultIsoString
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.net.HttpURLConnection
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailFragmentPresenter(var view: MovieDetailFragmentContract.View?,
                                   val movieService: MovieService,
                                   private var movieId: Int) : MovieDetailFragmentContract.Presenter {
    private val compositeDisposable : CompositeDisposable = CompositeDisposable()
    private var movieDetail: MovieDetail? = null
    private var trailerVideo: Video? = null

    override fun start() {
        loadDetails()
        view?.setupListeners()
    }

    override fun onPosterLoaded(): () -> Unit = {

    }

    override fun loadDetails() {
        val disposable = movieService
                .getDetails(movieId, ServiceConfig.API_KEY, Locale.getDefault().getDefaultIsoString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onLoadDetails(it) } ,
                        { onRequestError(it) }
                )

        compositeDisposable.add(disposable)
    }

    private fun onLoadDetails(response: Response<MovieDetail>) {
        when (response.code()) {
            HttpURLConnection.HTTP_OK -> onMovieDetailOk(response.body())
        }
    }

    private fun onLoadVideos(response: Response<Videos>) {
        when (response.code()) {
            HttpURLConnection.HTTP_OK -> onVideoOk(response.body())
        }
    }

    private fun onMovieDetailOk(detail: MovieDetail?) {
        movieDetail = detail

        val releaseYear = SimpleDateFormat("yyyy", Locale.getDefault()).format(movieDetail?.releaseDate)

        view?.setMovieOriginalTitle(movieDetail?.originalTitle, releaseYear)
        view?.setMovieOverview(movieDetail?.overview)
        view?.setMovieTitle(movieDetail?.title)
        view?.setMovieCover(ServiceConfig.IMAGE_BASE_URL + movieDetail?.posterPath)
        view?.setMovieGenres(getGenresAsString())

        loadTrailerPath()
    }

    private fun onVideoOk(videos: Videos?) {
        val youtubeVideo = getYoutubeTrailer(videos)

        if(youtubeVideo != null) {
            view?.showTrailerButton()
            trailerVideo = youtubeVideo
        }
    }

    private fun getYoutubeTrailer(videos: Videos?) : Video? {
        videos?.let {
            for (video in it.videos) {
                if("TRAILER".equals(video.type, true) && "YOUTUBE".equals(video.site, true)) return video
            }
        }

        return null
    }

    fun loadTrailerPath() {
        val disposable = movieService
                .getVideos(movieId, ServiceConfig.API_KEY, Locale.getDefault().getDefaultIsoString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { onLoadVideos(it) } ,
                        { }
                )

        compositeDisposable.add(disposable)
    }

    private fun getGenresAsString() : String {
        movieDetail?.let {
            val genres = TextUtils.join(" | ", it.genres)

            return genres.toString()
        }

        return ""
    }

    private fun onRequestError(error: Throwable) {
        // TODO
    }

    override fun onTrailerClicked() {
        if(trailerVideo != null) {
            view?.startTrailer(trailerVideo!!.key)
        }
    }
}