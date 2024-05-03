package com.gracodev.macromovies.utils

import android.widget.TextView
import com.gracodev.data.moviedata.Movie
import java.text.SimpleDateFormat
import java.util.Locale

fun Movie.getPosterUrl(): String {
    val baseUrl = "https://image.tmdb.org/t/p/"
    val imageSize = "w500"
    return baseUrl + imageSize + poster_path
}

fun Movie.getBackdropUrl(): String {
    val baseUrl = "https://image.tmdb.org/t/p/"
    val imageSize = "w500"
    return baseUrl + imageSize + backdrop_path
}

fun String.limitTextLength(maxLength: Int): String {
    return if (length > maxLength) {
        substring(0, maxLength) + "..."
    } else {
        this
    }
}

fun String.formatDateToYear(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("(yyyy)", Locale.getDefault())
    val date = inputFormat.parse(this)
    return outputFormat.format(date!!)
}