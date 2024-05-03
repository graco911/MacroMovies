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
                return try {

                    val position = params.key ?: 1
                    val response = movieAPIDataSource.fetchNowPlayingMovies(position)

                    if (response is UseCaseResult.Success) {
                        LoadResult.Page(
                            data = response.data.results,
                            prevKey = if (position == 1) null else (position - 1),
                            nextKey = if (position == response.data.total_pages) null else (position + 1)
                        )
                    } else {
                        LoadResult.Error(Exception("Failed to fetch Movie List"))
                    }
                } catch (e: Exception) {
                    LoadResult.Error(e)
                }
            }

            override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
                return state.anchorPosition?.let {
                    state.closestPageToPosition(it)?.prevKey?.plus(1)
                        ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
                }
            }
        }
    }
}