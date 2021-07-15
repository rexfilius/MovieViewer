package com.github.rexfilius.movieviewer.ui.movieList

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rexfilius.movieviewer.R
import com.github.rexfilius.movieviewer.data.models.Result
import com.github.rexfilius.movieviewer.databinding.FragmentMovieListBinding
import com.github.rexfilius.movieviewer.util.ApiResult
import com.github.rexfilius.movieviewer.util.ApiResult.*

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var movieListBinding: FragmentMovieListBinding? = null
    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListAdapter = MovieListAdapter { movieListAdapterOnClick(it) }

        val binding = FragmentMovieListBinding.bind(view)
        movieListBinding = binding

        binding.movieListRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = movieListAdapter
            setHasFixedSize(true)
        }

        viewModel.getTopRatedMovies().observe(viewLifecycleOwner, { moviesTopRated ->
            when (moviesTopRated) {
                Loading -> {
                    Toast.makeText(context, "Loading data", Toast.LENGTH_LONG).show()
                }

                is Success -> {
                    movieListAdapter.submitList(moviesTopRated.data.results)
                    movieListAdapter.notifyDataSetChanged()
                }

                is Failure -> {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_LONG).show()
                }
            }

        })

    }

    override fun onDestroyView() {
        movieListBinding = null
        super.onDestroyView()
    }

    private fun movieListAdapterOnClick(result: Result) {
        this.findNavController().navigate(
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment()
        )
    }

}