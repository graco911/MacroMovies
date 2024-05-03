package com.gracodev.data.repository

import com.gracodev.data.moviedata.Movie
import com.gracodev.data.remote.MovieAPIDataSource
import com.gracodev.data.usecaseresult.UseCaseResult

class MovieRepository(
    private val movieAPIDataSource: MovieAPIDataSource
) {
    suspend fun fetchNowPlayingMoviesList(offset: Int): UseCaseResult<List<Movie>> {
        return when (val response = movieAPIDataSource.fetchNowPlayingMovies(offset)) {
            is UseCaseResult.Error -> {
                return fetchNowPlayingMoviesListOffline()
            }

            is UseCaseResult.Success -> {
                return UseCaseResult.Success(response.data.results)
            }
        }
    }

    private fun fetchNowPlayingMoviesListOffline(): UseCaseResult<List<Movie>> {
        TODO("Not yet implemented")
    }
}