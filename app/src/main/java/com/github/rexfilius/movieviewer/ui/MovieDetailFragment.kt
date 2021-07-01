package com.github.rexfilius.movieviewer.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.rexfilius.movieviewer.R
import com.github.rexfilius.movieviewer.databinding.FragmentMovieDetailBinding

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private var movieDetailBinding: FragmentMovieDetailBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailBinding.bind(view)
        movieDetailBinding = binding
    }

    override fun onDestroyView() {
        movieDetailBinding = null
        super.onDestroyView()
    }

}