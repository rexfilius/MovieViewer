package com.github.rexfilius.movieviewer.data.repositories

import androidx.lifecycle.LiveData
import com.github.rexfilius.movieviewer.models.MovieDetail
import com.github.rexfilius.movieviewer.models.MoviesTopRated
import com.github.rexfilius.movieviewer.models.Result
import com.github.rexfilius.movieviewer.util.Resource

interface Repository {

    // functions for remote data source
    suspend fun getTopRatedMoviesFromApi(): Resource<MoviesTopRated>

    suspend fun getMovieDetailFromApi(movieId: Int): Resource<MovieDetail>

    // functions for local data source
    suspend fun insertMovieInDB(result: Result)

    suspend fun deleteMovieInDB(result: Result)

    fun findMovieByIdInDB(id: Int): LiveData<Result>

    fun getAllMoviesInDB(): LiveData<List<Result>>

}