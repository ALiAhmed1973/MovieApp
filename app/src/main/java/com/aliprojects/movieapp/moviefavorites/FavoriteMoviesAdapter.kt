package com.aliprojects.movieapp.moviefavorites

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aliprojects.movieapp.databinding.FavoriteItemBinding
import com.aliprojects.movieapp.models.Movie

private const val TAG = "FavoriteMoviesAdapter"
class FavoriteMoviesAdapter(private val viewModel: FavoriteMoviesViewModel): ListAdapter<Movie, FavoriteMoviesAdapter.FavMovieViewHolder>(DiffCallback) {
    object DiffCallback: DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title==newItem.title
        }

    }

    lateinit var movieCardClickListener:(Movie)->Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteMoviesAdapter.FavMovieViewHolder {
        return FavMovieViewHolder(FavoriteItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FavoriteMoviesAdapter.FavMovieViewHolder, position: Int) {
        val movie = getItem(position)
        Log.d(TAG, "onBindViewHolder: $movie")
        holder.bind(movie)
    }
    inner class FavMovieViewHolder(private var binding: FavoriteItemBinding): RecyclerView.ViewHolder(binding.root) {
        lateinit var itemMovie:Movie
        fun bind(movie: Movie)
        {
            itemMovie=movie
            binding.movie=movie
            binding.executePendingBindings()

        }
        init {
            binding.cardMovie.setOnClickListener{
                movieCardClickListener(itemMovie)
            }
            binding.iconFav.setOnClickListener{
                viewModel.removeMovieFromFavorites(itemMovie)
            }
        }

    }
    fun getMovie(func:(Movie)->Unit)
    {
        movieCardClickListener = func
    }
}