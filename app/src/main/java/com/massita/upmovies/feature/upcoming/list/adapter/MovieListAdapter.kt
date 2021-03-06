package com.massita.upmovies.feature.upcoming.list.adapter

import android.graphics.drawable.BitmapDrawable
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.massita.upmovies.R
import com.massita.upmovies.api.model.Movie
import com.massita.upmovies.api.service.ServiceConfig
import com.massita.upmovies.extension.load
import com.massita.upmovies.extension.setPaletteColor
import com.massita.upmovies.extension.toLocalDateString
import kotlinx.android.synthetic.main.item_list_movie.view.*

class MovieListAdapter(private val movies: MutableList<Movie>, val listener: (Int, View) -> Unit) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.bind(movie, position, listener)
    }

    fun getItem(position: Int) : Movie {
        return movies[position]
    }

    fun addAll(list : Collection<Movie>) {
        movies.addAll(list)
    }

    fun removeAll() {
        movies.clear()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, pos: Int, listener: (Int, AppCompatImageView) -> Unit) = with(itemView) {
            textMovieTitle.text = movie.title
            textMovieReleaseDate.text = movie.releaseDate.toLocalDateString()
            coverImage.load(ServiceConfig.IMAGE_BASE_URL + movie.backdropPath, onLoadImage())

            itemView.setOnClickListener { listener(pos, coverImage) }
        }

        fun onLoadImage() : () -> Unit = {
            itemView.movieHolder.setPaletteColor((itemView.coverImage.drawable as BitmapDrawable).bitmap)
        }

    }
}