package com.github.rexfilius.movieviewer.ui.movieDetail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.rexfilius.movieviewer.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tag = MovieDetailViewModel::class.java.name

    fun getMovieDetail(movieId: Int) = liveData(Dispatchers.IO) {
        val response = movieRepository.getMovieDetailFromApi(movieId)
        Log.d(tag, "getMovieDetail: $movieId")    // e.g. 19404
        emit(response)
    }

}