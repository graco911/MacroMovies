package com.gracodev.macromovies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.gracodev.data.moviedata.Movie
import com.gracodev.macromovies.R
import com.gracodev.macromovies.databinding.FragmentMovieDetailBinding
import com.gracodev.macromovies.utils.formatDateToYear
import com.gracodev.macromovies.utils.getBackdropUrl
import com.gracodev.macromovies.utils.getPosterUrl
import com.gracodev.macromovies.utils.limitTextLength

class MovieDetailFragment : BaseFragment() {

    override var TAG: String = this.javaClass.name

    private val binding: FragmentMovieDetailBinding by lazy {
        FragmentMovieDetailBinding.inflate(layoutInflater)
    }

    private lateinit var movie: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        extractBundleInfo()
        setMovieData()
    }

    private fun setMovieData() {
        binding.apply {
            Glide
                .with(requireContext())
                .load(movie.getBackdropUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imgToolbar);

            Glide
                .with(requireContext())
                .load(movie.getPosterUrl())
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageViewPoster);

            textTitle.text = movie.title.limitTextLength(15)
            textDescription.text = movie.overview
            textYear.text = movie.release_date.formatDateToYear()
            ratingMovie.rating = (movie.vote_average * .5f).toFloat()

        }
    }

    private fun extractBundleInfo() {

        arguments?.getParcelable<Movie>("MOVIE_DATA").let {
            movie = it!!
        }
    }
}