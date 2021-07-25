package com.github.rexfilius.movieviewer.ui.movieList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.github.rexfilius.movieviewer.data.models.Result
import com.github.rexfilius.movieviewer.databinding.ItemMovieListBinding
import com.github.rexfilius.movieviewer.util.Constants.BASE_URL_IMAGE

class MovieListAdapter(private val onClick: (Result) -> Unit) :
    ListAdapter<Result, MovieListAdapter.MovieListViewHolder>(MovieListDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        return MovieListViewHolder(
            ItemMovieListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result, onClick)
    }

    inner class MovieListViewHolder(private val binding: ItemMovieListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result, onClick: (Result) -> Unit) {
            binding.movieListCardImage.load(BASE_URL_IMAGE + result.moviePoster)
            binding.movieListCardTitle.text = result.movieTitle

            binding.root.setOnClickListener { onClick(result) }
        }

    }

}

object MovieListDiffUtil : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.movieId == newItem.movieId
    }

}