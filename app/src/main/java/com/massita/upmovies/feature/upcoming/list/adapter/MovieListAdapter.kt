package com.massita.upmovies.feature.upcoming.list.adapter

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.massita.upmovies.R
import com.massita.upmovies.api.model.Movie
import com.massita.upmovies.extension.load
import com.massita.upmovies.extension.setPaletteColor
import kotlinx.android.synthetic.main.item_list_movie.view.*

class MovieListAdapter(private val movies: MutableList<Movie>, val listener: (Int) -> Unit) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.bind(movie, position, listener)
    }

    fun addAll(list : Collection<Movie>) {
        movies.addAll(list)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), RequestListener<Drawable> {

        fun bind(movie: Movie, pos: Int, listener: (Int) -> Unit) = with(itemView) {
            textMovieTitle.text = movie.title
            textMovieReleaseDate.text = movie.releaseDate
            coverImage.load("http://image.tmdb.org/t/p/w780" + movie.backdropPath, this@ViewHolder)


            itemView.setOnClickListener { listener(pos) }
        }

        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
            return false
        }

        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
            itemView.movieHolder.setPaletteColor((resource as BitmapDrawable).bitmap)
            return false
        }

    }
}