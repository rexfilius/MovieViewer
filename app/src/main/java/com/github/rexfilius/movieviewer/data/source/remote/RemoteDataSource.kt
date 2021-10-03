package com.github.rexfilius.movieviewer.data.source.remote

import com.github.rexfilius.movieviewer.models.MovieDetail
import com.github.rexfilius.movieviewer.models.MoviesTopRated

interface RemoteDataSource {

    suspend fun getTopRatedMoviesFromApi(): MoviesTopRated

    suspend fun getMovieDetailFromApi(movieId: Int): MovieDetail

}