package com.github.rexfilius.movieviewer.util

sealed class Resource<out T> {

    object Loading : Resource<Nothing>()
    data class Success<T>(val data: T, val msg: String) : Resource<T>()
    data class Failure(val error: Throwable) : Resource<Nothing>()

}
