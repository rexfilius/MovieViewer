package com.github.rexfilius.movieviewer.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.rexfilius.movieviewer.data.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel : ViewModel() {

    private val repository get() = MovieRepository

    private val tag = MovieViewModel::class.java.simpleName

    fun getTopRatedMovies() = liveData(Dispatchers.IO) {
        val response = repository.getTopRatedMovies()
        Log.d(tag, "getTopRatedMovies: $response")
        emit(response)
    }

    fun getMovieDetail(movieId: Int) = liveData(Dispatchers.IO) {
        val response = repository.getMovieDetail(movieId)
        Log.d(tag, "getMovieDetail: $movieId")
        emit(response)
    }
    // 19404

}