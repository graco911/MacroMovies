package com.gracodev.data.database

import androidx.paging.PagingSource
import com.gracodev.data.moviedata.Movie
import com.gracodev.data.usecaseresult.UseCaseResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class MovieRoomDataSource(
    private val iMovieRoom: IMovieRoom,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun fetchMovieList(): UseCaseResult<List<Movie>> =
        withContext(ioDispatcher) {
            try {
                iMovieRoom.fetchMovieList()
            } catch (ex: Exception) {
                UseCaseResult.Error(ex)
            }
        }

    suspend fun fetchMoviePagingList(): PagingSource<Int, Movie> =
        withContext(ioDispatcher) {
            iMovieRoom.fetchMoviePagingList()
        }

    suspend fun deleteDatabase(): UseCaseResult<Unit> =
        withContext(ioDispatcher) {
            try {
                iMovieRoom.deleteDatabase()
            } catch (ex: Exception) {
                UseCaseResult.Error(ex)
            }
        }

    suspend fun insertMovie(movie: Movie): UseCaseResult<Long> =
        withContext(ioDispatcher) {
            try {
                iMovieRoom.insertMovie(movie)
            } catch (ex: Exception) {
                UseCaseResult.Error(ex)
            }
        }
}

interface IMovieRoom {
    suspend fun fetchMovieList(): UseCaseResult<List<Movie>>
    suspend fun fetchMoviePagingList(): PagingSource<Int, Movie>
    suspend fun deleteDatabase(): UseCaseResult<Unit>
    suspend fun insertMovie(movieData: Movie): UseCaseResult<Long>
}