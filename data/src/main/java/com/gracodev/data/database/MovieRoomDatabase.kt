package com.gracodev.data.database

import androidx.paging.PagingSource
import com.gracodev.data.dao.MovieDAO
import com.gracodev.data.moviedata.Movie
import com.gracodev.data.usecaseresult.UseCaseResult

class MovieRoomDatabase(private val movieDAO: MovieDAO) : IMovieRoom {
    override suspend fun fetchMovieList(): UseCaseResult<List<Movie>> {
        return UseCaseResult.Success(movieDAO.getAllMovies())
    }

    override suspend fun fetchMoviePagingList(): PagingSource<Int, Movie> {
        return movieDAO.getPagingMovies()
    }

    override suspend fun deleteDatabase(): UseCaseResult<Unit> {
        return UseCaseResult.Success(movieDAO.deleteAll())
    }

    override suspend fun insertMovie(movieData: Movie): UseCaseResult<Long> {
        return UseCaseResult.Success(movieDAO.insert(movieData))
    }
}