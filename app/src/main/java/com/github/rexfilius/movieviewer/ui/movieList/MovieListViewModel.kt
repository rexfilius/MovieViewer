package com.github.rexfilius.movieviewer.ui.movieList

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.rexfilius.movieviewer.data.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tag = MovieListViewModel::class.java.simpleName

    fun getTopRatedMovies() = liveData(Dispatchers.IO) {
        val response = movieRepository.getTopRatedMoviesFromApi()
        Log.d(tag, "getTopRatedMovies: $response")
        emit(response)
    }

}