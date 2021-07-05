package com.github.rexfilius.movieviewer.ui.movieDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.rexfilius.movieviewer.R
import com.github.rexfilius.movieviewer.databinding.FragmentMovieDetailBinding
import com.github.rexfilius.movieviewer.util.ApiResult
import com.github.rexfilius.movieviewer.util.Constants.FAILURE
import com.github.rexfilius.movieviewer.util.Constants.LOADING
import com.github.rexfilius.movieviewer.util.Constants.SUCCESS
import com.github.rexfilius.movieviewer.util.toast

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var movieDetailBinding: FragmentMovieDetailBinding? = null
    private val viewModel: MovieDetailViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailBinding.bind(view)
        movieDetailBinding = binding

        val movieId = args.movieID

        viewModel.getMovieDetail(movieId).observe(viewLifecycleOwner, { movieDetail ->
            when (movieDetail) {
                ApiResult.Loading -> LOADING.toast(requireContext())
                is ApiResult.Failure -> FAILURE.toast(requireContext())

                is ApiResult.Success -> {
                    SUCCESS.toast(requireContext())
                    binding.movieDetailTitleText.text = movieDetail.data.movieTitle
                    binding.movieDetailReleaseDateText.text = movieDetail.data.movieReleaseDate
                    binding.movieDetailOverviewText.text = movieDetail.data.movieOverview
                }
            }
        })
    }

    override fun onDestroyView() {
        movieDetailBinding = null
        super.onDestroyView()
    }

}