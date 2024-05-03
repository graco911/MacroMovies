package com.gracodev.domain.usecase

import android.location.GnssAntennaInfo.PhaseCenterOffset
import com.gracodev.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchMovieListUseCase(
    private val movieRepository: MovieRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke(page: Int) = withContext(defaultDispatcher) {
        movieRepository.fetchNowPlayingMoviesList(page)
    }
}