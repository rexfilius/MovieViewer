package com.github.rexfilius.movieviewer.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rexfilius.movieviewer.R
import com.github.rexfilius.movieviewer.databinding.FragmentMovieListBinding
import com.github.rexfilius.movieviewer.util.ApiResult

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var movieListBinding: FragmentMovieListBinding? = null
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)
        movieListAdapter = MovieListAdapter()

        val binding = FragmentMovieListBinding.bind(view)
        movieListBinding = binding

        binding.movieListRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = movieListAdapter
            setHasFixedSize(true)
        }

        viewModel.getTopRatedMovies().observe(viewLifecycleOwner, { moviesTopRated ->
            when (moviesTopRated) {
                ApiResult.Loading -> {
                    Toast.makeText(context, "Loading data", Toast.LENGTH_LONG).show()
                }

                is ApiResult.Success -> {
                    movieListAdapter.submitList(moviesTopRated.data.results)
                    movieListAdapter.notifyDataSetChanged()
                }

                is ApiResult.Failure -> {
                    Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_LONG).show()
                }
            }

        })

    }

    override fun onDestroyView() {
        movieListBinding = null
        super.onDestroyView()
    }

}