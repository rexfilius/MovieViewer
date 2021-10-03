package com.github.rexfilius.movieviewer.data.source.local

import androidx.lifecycle.LiveData
import com.github.rexfilius.movieviewer.models.Result
import com.github.rexfilius.movieviewer.util.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieLocalSource @Inject constructor(
    private val movieDao: MovieDao,
    private val dispatcher: DispatcherProvider
) : LocalDataSource {

    override suspend fun insertMovieInDB(result: Result) =
        withContext(dispatcher.io) {
            movieDao.insertMovie(result)
        }

    override suspend fun deleteMovieInDB(result: Result) =
        withContext(dispatcher.io) {
            movieDao.deleteMovie(result)
        }

    override fun findMovieByIdInDB(id: Int): LiveData<Result> {
        return movieDao.findMovieById(id)
    }

    override fun getAllMoviesInDB(): LiveData<List<Result>> {
        return movieDao.getAllMovies()
    }

}