package com.gracodev.domain.usecase

import com.gracodev.data.repository.MoviePagingRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FetchMoviePagingListUseCase(
    private val moviePagingRepository: MoviePagingRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend operator fun invoke() = withContext(defaultDispatcher) {
        moviePagingRepository.fechNowPlayingMoviewListFromAPI()
    }
}