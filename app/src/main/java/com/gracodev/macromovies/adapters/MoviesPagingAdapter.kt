package com.gracodev.macromovies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gracodev.data.moviedata.Movie
import com.gracodev.macromovies.databinding.MovieItemBinding
import com.gracodev.macromovies.viewholders.MovieViewHolder

class MoviesPagingAdapter(
    private val onItemClick: (Movie) -> Unit
) :
    PagingDataAdapter<Movie, MovieViewHolder>(MovieComparator()) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it) { item ->
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(
            MovieItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    class MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {

            return oldItem == newItem
        }
    }
}