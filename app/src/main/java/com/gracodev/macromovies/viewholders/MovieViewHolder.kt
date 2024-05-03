package com.gracodev.macromovies.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gracodev.data.moviedata.Movie
import com.gracodev.macromovies.R
import com.gracodev.macromovies.databinding.MovieItemBinding
import com.gracodev.macromovies.utils.getPosterUrl

class MovieViewHolder(private val binding: MovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie, click: (Movie) -> Unit) {
        binding.apply {
            Glide.with(binding.root.context)
                .load(movie.getPosterUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageViewPoster)
        }
    }
}