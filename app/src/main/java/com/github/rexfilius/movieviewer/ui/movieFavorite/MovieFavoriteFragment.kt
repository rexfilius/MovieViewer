package com.github.rexfilius.movieviewer.ui.movieFavorite

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rexfilius.movieviewer.R
import com.github.rexfilius.movieviewer.databinding.FragmentMovieFavoriteBinding
import com.github.rexfilius.movieviewer.models.Result
import com.github.rexfilius.movieviewer.ui.MovieAdapter
import com.github.rexfilius.movieviewer.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFavoriteFragment : Fragment(R.layout.fragment_movie_favorite) {

    private var movieFavoriteBinding: FragmentMovieFavoriteBinding? = null
    private val viewModel: MovieFavoriteViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieFavoriteBinding.bind(view)
        movieFavoriteBinding = binding

        movieAdapter = MovieAdapter(requireContext()) { result ->
            navigateToMovieDetail(result)
            //deleteMovie(result)
        }

        binding.movieFavoriteRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = movieAdapter
            setHasFixedSize(true)
        }

        viewModel.getAllMoviesInDB().observe(viewLifecycleOwner, {
            movieAdapter.submitList(it)
            //binding.movieFavoriteNoFavText.visibility = View.GONE
            binding.movieFavoriteRecyclerView.visibility = View.VISIBLE
            Log.d("MovieFavoriteFragment", "onViewCreated: $it")
        })
    }

    override fun onDestroyView() {
        movieFavoriteBinding = null
        super.onDestroyView()
    }

    private fun navigateToMovieDetail(result: Result) {
        this.findNavController().navigate(
            MovieFavoriteFragmentDirections.actionMovieFavoriteFragmentToMovieDetailFragment(
                result.movieId
            )
        )
    }

    private fun deleteMovie(result: Result) {
        if (!(result.isFavorite)) {
            viewModel.deleteMovieInDB(result)
            "Movie removed from favorites".toast(requireContext())
        }
    }

}