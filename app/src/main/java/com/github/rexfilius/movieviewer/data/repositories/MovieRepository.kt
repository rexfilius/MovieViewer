package com.github.rexfilius.movieviewer.data.repositories

import com.github.rexfilius.movieviewer.data.models.MovieDetail
import com.github.rexfilius.movieviewer.data.models.Result
import com.github.rexfilius.movieviewer.data.remote.RetrofitClient
import com.github.rexfilius.movieviewer.util.ApiResult

object MovieRepository : Repository {

    private val retrofitClient = RetrofitClient.retrofit

    override suspend fun getTopRatedMovies(): ApiResult<List<Result>> {
        return try {
            val response = retrofitClient.getTopRatedMovies()
            ApiResult.Success(response, "Top rated movies fetched successfully")
        } catch (e: Throwable) {
            ApiResult.Failure(e)
        }
    }

    override suspend fun getMovieDetail(movieId: Int): ApiResult<MovieDetail> {
        return try {
            val response = retrofitClient.getMovieDetail(movieId)
            ApiResult.Success(response, "Movie Detail with id $movieId fetched successfully")
        } catch (e: Throwable) {
            ApiResult.Failure(e)
        }
    }


}