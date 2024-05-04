package com.gracodev.data.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gracodev.data.moviedata.Movie


@Dao
interface MovieDAO {
    @Insert
    suspend fun insert(moviewItem: Movie): Long

    @Query("SELECT * FROM movies_list ORDER BY movieId")
    fun getPagingMovies(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movies_list")
    suspend fun getAllMovies(): List<Movie>

    @Query("DELETE FROM movies_list")
    suspend fun deleteAll()
}