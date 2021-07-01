package com.github.rexfilius.movieviewer.data

import com.github.rexfilius.movieviewer.data.models.MovieDetail
import com.github.rexfilius.movieviewer.data.models.Result
import com.github.rexfilius.movieviewer.util.ApiResult

interface Repository {

    suspend fun getTopRatedMovies(): ApiResult<List<Result>>

    suspend fun getMovieDetail(movieId: Int): ApiResult<MovieDetail>

}