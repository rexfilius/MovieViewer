package com.github.rexfilius.movieviewer.data.repositories

import com.github.rexfilius.movieviewer.models.MovieDetail
import com.github.rexfilius.movieviewer.models.MoviesTopRated
import com.github.rexfilius.movieviewer.util.Resource

// My Thoughts::
// This FakeRepository test class will be used for the ViewModel
// it has no business with the MovieRepository
class FakeRepository : Repository {

    override suspend fun getTopRatedMovies(): Resource<MoviesTopRated> {
        TODO("Not yet implemented")
    }

    override suspend fun getMovieDetail(movieId: Int): Resource<MovieDetail> {
        TODO("Not yet implemented")
    }

}