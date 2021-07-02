package com.github.rexfilius.movieviewer.data.repositories

import com.github.rexfilius.movieviewer.data.models.MovieDetail
import com.github.rexfilius.movieviewer.data.models.MoviesTopRated
import com.github.rexfilius.movieviewer.data.models.Result
import com.github.rexfilius.movieviewer.util.ApiResult

interface Repository {

    suspend fun getTopRatedMovies(): ApiResult<MoviesTopRated>

    suspend fun getMovieDetail(movieId: Int): ApiResult<MovieDetail>

}