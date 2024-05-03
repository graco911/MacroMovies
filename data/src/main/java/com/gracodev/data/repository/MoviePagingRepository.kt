package com.gracodev.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gracodev.data.moviedata.Movie
import com.gracodev.data.remote.MovieAPIDataSource
import com.gracodev.data.usecaseresult.UseCaseResult

class MoviePagingRepository(private val movieAPIDataSource: MovieAPIDataSource) {
    suspend fun fechNowPlayingMoviewListFromAPI(): PagingSource<Int, Movie> {
        return object : PagingSource<Int, Movie>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

                val offset = params.key ?: 0
                val limit = params.loadSize

                return try {
                    val response = movieAPIDataSource.fetchNowPlayingMovies(offset)
                    if (response is UseCaseResult.Success) {
                        LoadResult.Page(
                            data = response.data.results,
                            prevKey = if (offset == 0) null else offset - limit,
                            nextKey = offset + limit
                        )
                    } else {
                        LoadResult.Error(Exception("Failed to fetch Movie List"))
                    }
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
                TODO("Not yet implemented")
            }
        }
    }
}