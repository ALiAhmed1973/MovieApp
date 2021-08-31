package com.aliprojects.movieapp.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliprojects.movieapp.databinding.MovieItemBinding
import com.aliprojects.movieapp.models.Movie

class MoviesAdapter: ListAdapter<Movie,MoviesAdapter.MovieViewHolder>(DiffCallback) {
    object DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title==newItem.title
        }

    }

    class MovieViewHolder(private var binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie)
        {
            binding.textMovieTitle.text= movie.title
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MovieViewHolder {
      return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        var movie = getItem(position)
        holder.bind(movie)
    }
}