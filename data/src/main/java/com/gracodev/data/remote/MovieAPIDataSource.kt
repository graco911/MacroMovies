package com.gracodev.data.remote

import com.gracodev.data.moviedata.NowPlayingResponse
import com.gracodev.data.usecaseresult.UseCaseResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieAPIDataSource(
    private val movieAPI: IMovieAPI,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun fetchNowPlayingMovies(offset: Int): UseCaseResult<NowPlayingResponse> =
        withContext(ioDispatcher) {
            movieAPI.fetchNowPlayingMoviesList(offset)
        }
}


interface IMovieAPI {
    suspend fun fetchNowPlayingMoviesList(offset: Int): UseCaseResult<NowPlayingResponse>
}