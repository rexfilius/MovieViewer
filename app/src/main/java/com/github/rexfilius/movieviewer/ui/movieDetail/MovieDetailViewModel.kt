package com.github.rexfilius.movieviewer.ui.movieDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.rexfilius.movieviewer.data.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers

class MovieDetailViewModel : ViewModel() {

    private val repository get() = MovieRepository

    private val tag = MovieDetailViewModel::class.java.name

    fun getMovieDetail(movieId: Int) = liveData(Dispatchers.IO) {
        val response = repository.getMovieDetail(movieId)
        Log.d(tag, "getMovieDetail: ${movieId == 19404}")    // e.g. 19404
        emit(response)
    }

}