package com.github.rexfilius.movieviewer.data.remote

import com.github.rexfilius.movieviewer.BuildConfig
import com.github.rexfilius.movieviewer.data.models.MovieDetail
import com.github.rexfilius.movieviewer.data.models.MoviesTopRated
import com.github.rexfilius.movieviewer.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("?")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("sort_by") popularity: String = Constants.POPULAR
    ): MoviesTopRated


    @GET("{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): MovieDetail

}


object RetrofitClient {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL_POPULAR)
            .build()
    }

    val retrofit: MovieAPI = getRetrofit().create(MovieAPI::class.java)
}



