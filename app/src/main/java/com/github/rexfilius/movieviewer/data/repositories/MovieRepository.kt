package com.github.rexfilius.movieviewer.data.repositories

import androidx.lifecycle.LiveData
import com.github.rexfilius.movieviewer.data.source.local.LocalDataSource
import com.github.rexfilius.movieviewer.data.source.remote.RemoteDataSource
import com.github.rexfilius.movieviewer.models.MovieDetail
import com.github.rexfilius.movieviewer.models.MoviesTopRated
import com.github.rexfilius.movieviewer.models.Result
import com.github.rexfilius.movieviewer.util.DispatcherProvider
import com.github.rexfilius.movieviewer.util.Resource
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieRemoteSource: RemoteDataSource,
    private val movieLocalSource: LocalDataSource,
    private val dispatcher: DispatcherProvider
) : Repository {

    override suspend fun getTopRatedMoviesFromApi(): Resource<MoviesTopRated> {
        return try {
            val response = movieRemoteSource.getTopRatedMoviesFromApi()
            Resource.Success(response, "Top rated movies fetched successfully")
        } catch (e: Throwable) {
            Resource.Failure(e)
        }
    }

    override suspend fun getMovieDetailFromApi(movieId: Int): Resource<MovieDetail> {
        return try {
            val response = movieRemoteSource.getMovieDetailFromApi(movieId)
            Resource.Success(response, "Movie Detail with id $movieId fetched successfully")
        } catch (e: Throwable) {
            Resource.Failure(e)
        }
    }

    override suspend fun insertMovieInDB(result: Result) =
        withContext(dispatcher.io) {
            movieLocalSource.insertMovieInDB(result)
        }

    override suspend fun deleteMovieInDB(result: Result) =
        withContext(dispatcher.io) {
            movieLocalSource.deleteMovieInDB(result)
        }

    override fun findMovieByIdInDB(id: Int): LiveData<Result> {
        return movieLocalSource.findMovieByIdInDB(id)
    }

    override fun getAllMoviesInDB(): LiveData<List<Result>> {
        return movieLocalSource.getAllMoviesInDB()
    }

}