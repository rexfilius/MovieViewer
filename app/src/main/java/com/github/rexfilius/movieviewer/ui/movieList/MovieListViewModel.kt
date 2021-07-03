package com.github.rexfilius.movieviewer.ui.movieList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.rexfilius.movieviewer.data.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieListViewModel : ViewModel() {

    private val repository get() = MovieRepository

    private val tag = MovieListViewModel::class.java.simpleName

    fun getTopRatedMovies() = liveData(Dispatchers.IO) {
        val response = repository.getTopRatedMovies()
        Log.d(tag, "getTopRatedMovies: $response")
        emit(response)
    }

}