package com.github.rexfilius.movieviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.rexfilius.movieviewer.data.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieViewModel : ViewModel() {

    private val repository get() = MovieRepository

    fun getTopRatedMovies() = liveData(Dispatchers.IO) {
        val response = repository.getTopRatedMovies()
        emit(response)
    }

    fun getMovieDetail(movieId: Int) = liveData(Dispatchers.IO) {
        val response = repository.getMovieDetail(movieId)
        emit(response)
    }

}