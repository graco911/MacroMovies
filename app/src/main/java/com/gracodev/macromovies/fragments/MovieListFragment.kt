package com.gracodev.macromovies.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.gracodev.data.moviedata.Movie
import com.gracodev.macromovies.adapters.FavoritesAdapter
import com.gracodev.macromovies.adapters.MoviesAdapter
import com.gracodev.macromovies.adapters.MoviesPagingAdapter
import com.gracodev.macromovies.databinding.FragmentMovieListBinding
import com.gracodev.macromovies.states.UIStates
import com.gracodev.macromovies.viewmodels.MovieViewModel
import kotlinx.coroutines.flow.collectLatest
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

    private val moviesPagingAdapter: MoviesPagingAdapter by lazy {
        MoviesPagingAdapter() {
            handleTap(it)
        }
    }

    private val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        binding.swipeRefreshLayout
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
        viewModel.fetchMoviePagingData()
    }

    private fun setUpSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchMoviePagingData()
        }
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
                            swipeRefreshLayout.isRefreshing = false
                            uiState.value?.let { favoriteListAdapter.submitAll(it.toMutableList()) }
                            uiState.value?.let { moviesListAdapter.submitAll(it.toMutableList()) }
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.pagingData.collectLatest { pagingData ->
                    swipeRefreshLayout.isRefreshing = false
                    lifecycleScope.launch {
                        moviesPagingAdapter.submitData(pagingData)
                    }
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.apply {
            //rvFavorites.adapter = moviesPagingAdapter
            rvMovies.adapter = moviesPagingAdapter
        }
    }
}