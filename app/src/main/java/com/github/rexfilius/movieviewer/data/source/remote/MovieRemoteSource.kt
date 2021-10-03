package com.github.rexfilius.movieviewer.data.source.remote

import com.github.rexfilius.movieviewer.models.MovieDetail
import com.github.rexfilius.movieviewer.models.MoviesTopRated
import javax.inject.Inject

class MovieRemoteSource @Inject constructor(
    private val movieAPI: MovieAPI
) : RemoteDataSource {

    override suspend fun getTopRatedMoviesFromApi(): MoviesTopRated {
        return movieAPI.getTopRatedMovies()
    }

    override suspend fun getMovieDetailFromApi(movieId: Int): MovieDetail {
        return movieAPI.getMovieDetail(movieId)
    }

}