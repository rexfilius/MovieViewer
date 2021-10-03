package com.github.rexfilius.movieviewer.ui.movieFavorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.rexfilius.movieviewer.R
import com.github.rexfilius.movieviewer.databinding.FragmentMovieFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFavoriteFragment : Fragment(R.layout.fragment_movie_favorite) {

    private var movieFavoriteBinding: FragmentMovieFavoriteBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieFavoriteBinding.bind(view)
        movieFavoriteBinding = binding
    }

    override fun onDestroyView() {
        movieFavoriteBinding = null
        super.onDestroyView()
    }

}