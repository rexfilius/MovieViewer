package com.github.rexfilius.movieviewer.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.rexfilius.movieviewer.R
import com.github.rexfilius.movieviewer.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment(R.layout.fragment_movie_list) {

    private var movieListBinding: FragmentMovieListBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieListBinding.bind(view)
        movieListBinding = binding
    }

    override fun onDestroyView() {
        movieListBinding = null
        super.onDestroyView()
    }

}