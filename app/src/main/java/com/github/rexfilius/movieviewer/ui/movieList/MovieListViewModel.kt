package com.github.rexfilius.movieviewer.ui.movieList

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.github.rexfilius.movieviewer.data.repositories.MovieRepository
import com.github.rexfilius.movieviewer.data.repositories.Repository
import com.github.rexfilius.movieviewer.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: Repository,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tag = MovieListViewModel::class.java.simpleName

    fun getTopRatedMovies() = liveData(Dispatchers.IO) {
        val response = movieRepository.getTopRatedMoviesFromApi()
        Log.d(tag, "getTopRatedMovies: $response")
        emit(response)
    }

    fun insertMovie(movie: Result) = viewModelScope.launch {
        movieRepository.insertMovieInDB(movie)
    }

    fun deleteMovie(movie: Result) = viewModelScope.launch {
        movieRepository.deleteMovieInDB(movie)
    }

}