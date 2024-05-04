package com.gracodev.macromovies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gracodev.data.moviedata.Movie
import com.gracodev.macromovies.databinding.MovieItemBinding
import com.gracodev.macromovies.viewholders.MovieViewHolder

class MoviesAdapter(
    private val favoritesList: MutableList<Movie> = mutableListOf(),
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {

    fun submitAll(newFavoritesList: MutableList<Movie>) {
        favoritesList.clear()
        favoritesList.addAll(newFavoritesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(favoritesList[position]) { movie ->
            onItemClick(movie)
        }
    }

    override fun getItemCount(): Int = favoritesList.size
}