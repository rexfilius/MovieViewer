package com.github.rexfilius.movieviewer.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.rexfilius.movieviewer.data.models.Result

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(result: Result)

    @Update
    suspend fun updateMovie(result: Result)

    @Delete
    suspend fun deleteMovie(result: Result)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Result>>

    @Query("SELECT * FROM movies WHERE movieId = :id")
    suspend fun findMovieById(id: Int): Result

}