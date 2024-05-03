package com.gracodev.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gracodev.data.dao.MovieDAO
import com.gracodev.data.moviedata.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
}