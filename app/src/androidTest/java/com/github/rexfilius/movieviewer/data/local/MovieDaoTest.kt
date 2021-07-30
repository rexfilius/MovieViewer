package com.github.rexfilius.movieviewer.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.github.rexfilius.movieviewer.data.models.Result
import com.github.rexfilius.movieviewer.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieDatabase: MovieDatabase
    private lateinit var movieDao: MovieDao

    @Before
    fun createDatabase() {
        movieDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MovieDatabase::class.java
        ).allowMainThreadQueries().build()
        movieDao = movieDatabase.movieDao
    }

    @After
    fun closeDatabase() = movieDatabase.close()

    @Test
    fun insertMovie() = runBlockingTest {
        val movie = Result(
            1, "Overview", "Poster",
            "Date", "Title", true
        )

        movieDao.insertMovie(movie)
        val allMovies = movieDao.getAllMovies().getOrAwaitValue()

        assertThat(allMovies).contains(movie)
    }

    @Test
    fun updateMovie() = runBlockingTest {
        val movie = Result(
            1, "Overview", "Poster",
            "Date", "Title", true
        )

        movieDao.insertMovie(movie)
        movie.isFavorite = false
        movieDao.updateMovie(movie)

        assertThat(movie.isFavorite).isEqualTo(false)
    }

    @Test
    fun deleteMovie() = runBlockingTest {
        val movie = Result(
            1, "Overview", "Poster",
            "Date", "Title", true
        )

        movieDao.insertMovie(movie)
        movieDao.deleteMovie(movie)
        val allMovies = movieDao.getAllMovies().getOrAwaitValue()

        assertThat(allMovies).doesNotContain(movie)
    }

    // this test keeps failing
    // TODO: Rewrite test properly
    @Test
    fun findMovieInDatabase(id: Int = 1) = runBlockingTest {
        val movie = Result(
            1, "Overview", "Poster",
            "Date", "Title", true
        )

        movieDao.insertMovie(movie)

        val newMovie = Result(
            id, "Overview", "Poster",
            "Date", "Title", true
        )

        movieDao.findMovieById(newMovie.movieId)
        //assertThat(movie.movieId).isEqualTo(s.movieId)

        //val allMovies = movieDao.getAllMovies().getOrAwaitValue()
        assertThat(movie).isSameInstanceAs(newMovie)
    }

    @Test
    fun getAllMovies() = runBlockingTest {
        val movie1 = Result(
            1, "Overview 1", "Poster 1",
            "Date 1", "Title 1", true
        )
        val movie2 = Result(
            2, "Overview 2", "Poster 2",
            "Date 2", "Title 2", true
        )

        movieDao.insertMovie(movie1)
        movieDao.insertMovie(movie2)
        val allMovies = movieDao.getAllMovies().getOrAwaitValue()

        assertThat(allMovies).containsExactly(movie1, movie2)
    }


}