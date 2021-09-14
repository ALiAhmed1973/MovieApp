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
class MoviesAdapter(private var movieCardClickListener: MovieCardClickListener): ListAdapter<Movie,MoviesAdapter.MovieViewHolder>(DiffCallback) {

    object DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem==newItem
        }

    }

    inner class MovieViewHolder(private var binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root) {
        lateinit var itemMovie:Movie
        fun bind(movie: Movie)
        {
            itemMovie=movie
            binding.movie=movie
            binding.executePendingBindings()

        }
        init {
            binding.cardMovie.setOnClickListener{
                movieCardClickListener.onclick(itemMovie)
            }
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
    class MovieCardClickListener(val listenerFun: (Movie)->Unit)
    {
        fun onclick(movie :Movie)= listenerFun(movie)
    }
}