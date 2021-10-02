package com.github.rexfilius.movieviewer.data.repositories

import com.github.rexfilius.movieviewer.models.MovieDetail
import com.github.rexfilius.movieviewer.models.MoviesTopRated
import com.github.rexfilius.movieviewer.util.Resource

interface Repository {

    suspend fun getTopRatedMovies(): Resource<MoviesTopRated>

    suspend fun getMovieDetail(movieId: Int): Resource<MovieDetail>

}