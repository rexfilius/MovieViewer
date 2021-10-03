package com.github.rexfilius.movieviewer.data.source.remote

import com.github.rexfilius.movieviewer.BuildConfig.API_KEY
import com.github.rexfilius.movieviewer.models.MovieDetail
import com.github.rexfilius.movieviewer.models.MoviesTopRated
import com.github.rexfilius.movieviewer.util.Constants.POPULAR
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("discover/movie?")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("sort_by") popularity: String = POPULAR
    ): MoviesTopRated


    @GET("movie/{movie_id}?")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
    ): MovieDetail

}
