package com.github.rexfilius.movieviewer.ui.movieFavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.rexfilius.movieviewer.data.repositories.Repository
import com.github.rexfilius.movieviewer.models.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieFavoriteViewModel @Inject constructor(
    private val movieRepository: Repository,
    val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tag = MovieFavoriteViewModel::class.java.simpleName

    fun insertMovieInDB(movie: Result) = viewModelScope.launch {
        movieRepository.insertMovieInDB(movie)
    }

    fun deleteMovieInDB(movie: Result) = viewModelScope.launch {
        movieRepository.deleteMovieInDB(movie)
    }

    fun getAllMoviesInDB(): LiveData<List<Result>> {
        return movieRepository.getAllMoviesInDB()
    }

    fun findMovieByIdInDB(movieId: Int): LiveData<Result> {
        return movieRepository.findMovieByIdInDB(movieId)
    }

}