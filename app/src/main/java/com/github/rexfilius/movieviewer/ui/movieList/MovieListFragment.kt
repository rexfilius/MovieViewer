package com.github.rexfilius.movieviewer.ui.movieList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.rexfilius.movieviewer.R
import com.github.rexfilius.movieviewer.models.Result
import com.github.rexfilius.movieviewer.databinding.FragmentMovieListBinding
import com.github.rexfilius.movieviewer.util.Resource.*
import com.github.rexfilius.movieviewer.util.Constants.FAILURE
import com.github.rexfilius.movieviewer.util.Constants.LOADING
import com.github.rexfilius.movieviewer.util.Constants.SUCCESS
import com.github.rexfilius.movieviewer.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var movieListBinding: FragmentMovieListBinding? = null
    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieListAdapter = MovieListAdapter(requireContext()) { movieListAdapterOnClick(it) }

        val binding = FragmentMovieListBinding.bind(view)
        movieListBinding = binding

        binding.movieListRecyclerView.apply {
            layoutManager = LinearLayoutManager(view.context)
            adapter = movieListAdapter
            setHasFixedSize(true)
        }

        viewModel.getTopRatedMovies().observe(viewLifecycleOwner, { moviesTopRated ->
            when (moviesTopRated) {
                Loading -> LOADING.toast(requireContext())

                is Failure -> FAILURE.toast(requireContext())

                is Success -> {
                    SUCCESS.toast(requireContext())
                    movieListAdapter.submitList(moviesTopRated.data.results)
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
            MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(
                result.movieId
            )
        )
    }

}