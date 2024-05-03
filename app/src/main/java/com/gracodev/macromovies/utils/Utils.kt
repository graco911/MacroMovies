package com.gracodev.macromovies.utils

import android.widget.TextView
import com.gracodev.data.moviedata.Movie

fun Movie.getPosterUrl(): String {
    val baseUrl = "https://image.tmdb.org/t/p/"
    val imageSize = "w200"
    return baseUrl + imageSize + poster_path
}

fun Movie.getBackdropUrl(): String {
    val baseUrl = "https://image.tmdb.org/t/p/"
    val imageSize = "w200"
    return baseUrl + imageSize + backdrop_path
}

fun TextView.limitTextLength() {
    val maxLength = 10
    if (text.length > maxLength) {
        text = text.substring(0, maxLength) + "..."
    }
}