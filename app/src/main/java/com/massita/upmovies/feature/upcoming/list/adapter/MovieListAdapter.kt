package com.massita.upmovies.feature.upcoming.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.massita.upmovies.R
import com.massita.upmovies.api.model.Movie
import kotlinx.android.synthetic.main.item_list_movie.view.*

class MovieListAdapter(private val movies: List<Movie>, val listener: (Int) -> Unit) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.bind(movie, position, listener)
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie, pos: Int, listener: (Int) -> Unit) = with(itemView) {
            textMovieTitle.text = movie.title
            textMovieReleaseDate.text = movie.releaseDate

            itemView.setOnClickListener { listener(pos) }
        }
    }
}