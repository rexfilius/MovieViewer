package com.github.rexfilius.movieviewer.data.remote

import com.github.rexfilius.movieviewer.BuildConfig.API_KEY
import com.github.rexfilius.movieviewer.data.models.MovieDetail
import com.github.rexfilius.movieviewer.data.models.MoviesTopRated
import com.github.rexfilius.movieviewer.util.Constants.BASE_URL
import com.github.rexfilius.movieviewer.util.Constants.POPULAR
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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


object RetrofitClient {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    val retrofit: MovieAPI = getRetrofit().create(MovieAPI::class.java)
}



