package com.gracodev.macromovies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gracodev.data.moviedata.Movie
import com.gracodev.macromovies.adapters.FavoritesAdapter
import com.gracodev.macromovies.adapters.MoviesAdapter
import com.gracodev.macromovies.databinding.FragmentMovieListBinding
import com.gracodev.macromovies.states.UIStates
import com.gracodev.macromovies.viewmodels.MovieViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class MovieListFragment : BaseFragment() {

    override var TAG: String = this.javaClass.name

    private val viewModel: MovieViewModel by activityViewModel()

    private val binding: FragmentMovieListBinding by lazy {
        FragmentMovieListBinding.inflate(layoutInflater)
    }

    private val favoriteListAdapter: FavoritesAdapter by lazy {
        FavoritesAdapter() {
            handleTap(it)
        }
    }

    private val moviesListAdapter: MoviesAdapter by lazy {
        MoviesAdapter() {
            handleTap(it)
        }
    }

    private fun handleTap(it: Movie) {


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObsevableUIState()
        setUpSwipeRefreshLayout()
        viewModel.fetchMovieList()
    }

    private fun setUpSwipeRefreshLayout() {

    }

    private fun setUpObsevableUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is UIStates.Error -> {

                        }

                        UIStates.Init -> {


                        }

                        UIStates.Loading -> {


                        }

                        is UIStates.Success -> {
                            uiState.value?.let { favoriteListAdapter.submitAll(it.toMutableList()) }
                            uiState.value?.let { moviesListAdapter.submitAll(it.toMutableList()) }
                        }
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.apply {
            rvFavorites.adapter = favoriteListAdapter
            rvMovies.adapter = moviesListAdapter
        }
    }
}