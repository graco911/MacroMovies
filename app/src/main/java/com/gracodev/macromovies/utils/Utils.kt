package com.gracodev.macromovies.utils

import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
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

fun View.snackbar(message: String) {
    val snackbar = Snackbar
        .make(this, message, Snackbar.LENGTH_LONG).also { snackbar ->
            snackbar.setAction("Aceptar") {
                snackbar.dismiss()
            }
        }
    snackbar.view.background = ContextCompat.getDrawable(context, android.R.color.black)
    snackbar.show()
}

fun Context.alert(
    @StyleRes style: Int = 0,
    dialogBuilder: MaterialAlertDialogBuilder.() -> Unit
) {
    MaterialAlertDialogBuilder(this, style)
        .apply {
            setCancelable(false)
            dialogBuilder()
            create()
            show()
        }
}

fun MaterialAlertDialogBuilder.negativeButton(
    text: String = "No",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNegativeButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

fun MaterialAlertDialogBuilder.positiveButton(
    text: String = "Yes",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setPositiveButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}

fun MaterialAlertDialogBuilder.neutralButton(
    text: String = "OK",
    handleClick: (dialogInterface: DialogInterface) -> Unit = { it.dismiss() }
) {
    this.setNeutralButton(text) { dialogInterface, _ -> handleClick(dialogInterface) }
}