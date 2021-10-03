package com.github.rexfilius.movieviewer.ui.movieDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.rexfilius.movieviewer.R
import com.github.rexfilius.movieviewer.databinding.FragmentMovieDetailBinding
import com.github.rexfilius.movieviewer.util.Resource.*
import com.github.rexfilius.movieviewer.util.Constants.FAILURE
import com.github.rexfilius.movieviewer.util.Constants.LOADING
import com.github.rexfilius.movieviewer.util.Constants.SUCCESS
import com.github.rexfilius.movieviewer.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var movieDetailBinding: FragmentMovieDetailBinding? = null
    private val viewModel: MovieDetailViewModel by viewModels()
    private val args: MovieDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailBinding.bind(view)
        movieDetailBinding = binding

        viewModel.getMovieDetail(args.movieID).observe(viewLifecycleOwner, { movieDetail ->
            when (movieDetail) {
                Loading -> LOADING.toast(requireContext())

                is Failure -> FAILURE.toast(requireContext())

                is Success -> {
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