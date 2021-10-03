package com.github.rexfilius.movieviewer.data.source.local

import androidx.lifecycle.LiveData
import com.github.rexfilius.movieviewer.models.Result

interface LocalDataSource {

    suspend fun insertMovieInDB(result: Result)

    suspend fun deleteMovieInDB(result: Result)

    fun findMovieByIdInDB(id: Int): LiveData<Result>

    fun getAllMoviesInDB(): LiveData<List<Result>>
}