package com.gracodev.macromovies.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gracodev.domain.usecase.FetchMovieListUseCase
import com.gracodev.domain.usecase.FetchMoviePagingListUseCase
import com.gracodev.macromovies.viewmodels.MovieViewModel

class MovieViewModelFactory(
    private val fetchMoviePagingListUseCase: FetchMoviePagingListUseCase,
    private val fetchMovieListUseCase: FetchMovieListUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(fetchMovieListUseCase, fetchMoviePagingListUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}