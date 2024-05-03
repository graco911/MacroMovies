package com.gracodev.data.remote

import com.gracodev.data.moviedata.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    /**
     * Define endpoint to get a pokemon list using pagination
     */
    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("page") page: Int
    ): Response<NowPlayingResponse>
}