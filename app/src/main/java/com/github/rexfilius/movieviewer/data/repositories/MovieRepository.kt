package com.github.rexfilius.movieviewer.data.repositories

import com.github.rexfilius.movieviewer.models.MovieDetail
import com.github.rexfilius.movieviewer.models.MoviesTopRated
import com.github.rexfilius.movieviewer.data.source.remote.RetrofitClient
import com.github.rexfilius.movieviewer.util.Resource

object MovieRepository : Repository {

    private val retrofitClient = RetrofitClient.retrofit

    override suspend fun getTopRatedMovies(): Resource<MoviesTopRated> {
        return try {
            val response = retrofitClient.getTopRatedMovies()
            Resource.Success(response, "Top rated movies fetched successfully")
        } catch (e: Throwable) {
            Resource.Failure(e)
        }
    }

    override suspend fun getMovieDetail(movieId: Int): Resource<MovieDetail> {
        return try {
            val response = retrofitClient.getMovieDetail(movieId)
            Resource.Success(response, "Movie Detail with id $movieId fetched successfully")
        } catch (e: Throwable) {
            Resource.Failure(e)
        }
    }


}