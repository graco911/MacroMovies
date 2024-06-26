package com.gracodev.data.moviedata

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class NowPlayingResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
) : Parcelable

@Parcelize
data class Dates(
    val maximum: String,
    val minimum: String
) : Parcelable

@Parcelize
@Entity(tableName = "movies_list")
data class Movie(
    @PrimaryKey(autoGenerate = true) val movieId: Long,
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
) : Parcelable

@Entity(tableName = "genres")
data class Genre(
    @PrimaryKey val id: Int,
    val name: String
)

@Entity(
    tableName = "movie_genre_join",
    primaryKeys = ["movieId", "genreId"],
    foreignKeys = [
        ForeignKey(
            entity = Movie::class,
            parentColumns = ["movieId"],
            childColumns = ["movieId"]
        ),
        ForeignKey(
            entity = Genre::class,
            parentColumns = ["id"],
            childColumns = ["genreId"]
        )
    ]
)
data class MovieGenreJoin(
    val movieId: Long,
    val genreId: Int
)