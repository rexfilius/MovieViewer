package com.github.rexfilius.movieviewer.util

sealed class ApiResult<out T> {

    object Loading : ApiResult<Nothing>()
    data class Success<T>(val data: T, val msg: String) : ApiResult<T>()
    data class Failure(val error: Throwable) : ApiResult<Nothing>()

}
