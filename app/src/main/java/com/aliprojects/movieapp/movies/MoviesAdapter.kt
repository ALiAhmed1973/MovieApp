package com.aliprojects.movieapp.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliprojects.movieapp.databinding.MovieItemBinding
import com.aliprojects.movieapp.models.Movie

private const val TAG = "MoviesAdapter"
class MoviesAdapter: ListAdapter<Movie,MoviesAdapter.MovieViewHolder>(DiffCallback) {

    object DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title==newItem.title
        }

    }

    inner class MovieViewHolder(private var binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie)
        {
            binding.movie=movie
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MovieViewHolder {
      return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
    }
}