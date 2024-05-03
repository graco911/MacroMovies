package com.gracodev.data.remote

import com.gracodev.data.moviedata.NowPlayingResponse
import com.gracodev.data.usecaseresult.UseCaseResult
import retrofit2.Response

class RetrofitMovieAPI(private val movieAPI: MovieAPI) : IMovieAPI {

    private suspend fun <T : Any> executeRequest(apiCall: suspend () -> Response<T>): UseCaseResult<T> {
        return try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    UseCaseResult.Success(body)
                } else {
                    UseCaseResult.Error(Exception("Response body is null"))
                }
            } else {
                UseCaseResult.Error(Exception("Error ${response.code()}: ${response.message()}"))
            }
        } catch (ex: Exception) {
            UseCaseResult.Error(ex)
        }
    }

    override suspend fun fetchNowPlayingMoviesList(offset: Int): UseCaseResult<NowPlayingResponse> {
        return executeRequest { movieAPI.getNowPlayingMovies(offset) }
    }
}