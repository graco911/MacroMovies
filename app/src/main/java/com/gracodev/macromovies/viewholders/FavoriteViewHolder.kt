package com.gracodev.macromovies.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gracodev.data.moviedata.Movie
import com.gracodev.macromovies.R
import com.gracodev.macromovies.databinding.FavoriteItemBinding
import com.gracodev.macromovies.utils.getPosterUrl
import com.gracodev.macromovies.utils.limitTextLength

class FavoriteViewHolder(private val binding: FavoriteItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Movie, click: (Movie) -> Unit) {
        binding.apply {
            Glide.with(binding.root.context)
                .load(movie.getPosterUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageViewPoster)

            textTitle.text = movie.title
            textTitle.limitTextLength()
            textYear.text = movie.release_date
            ratingMovie.rating = (movie.vote_average * .5f).toFloat()
        }

        binding.root.setOnClickListener { click(movie) }
    }
}