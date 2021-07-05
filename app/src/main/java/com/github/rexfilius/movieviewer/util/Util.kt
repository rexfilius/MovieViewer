package com.github.rexfilius.movieviewer.util

import android.content.Context
import android.widget.Toast

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val POPULAR = "popularity.desc"
    const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original"

    const val LOADING = "Loading data"
    const val SUCCESS = "Success"
    const val FAILURE = "Failed to fetch data"
}

fun Any.toast(context: Context, duration: Int = Toast.LENGTH_LONG): Toast {
    return Toast.makeText(context, this.toString(), duration).apply { show() }
}
