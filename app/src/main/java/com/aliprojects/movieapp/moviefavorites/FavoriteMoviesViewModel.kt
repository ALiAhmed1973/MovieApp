package com.aliprojects.movieapp.moviefavorites

import android.util.Log
import androidx.lifecycle.*
import com.aliprojects.movieapp.models.Movie
import com.aliprojects.movieapp.repository.MoviesRepository
import kotlinx.coroutines.launch

private const val TAG = "FavoriteMoviesViewModel"

class FavoriteMoviesViewModel(private val repository: MoviesRepository) : ViewModel() {

    val favMovies: LiveData<List<Movie>> = repository.favMovies

    fun removeMovieFromFavorites(movie: Movie) {
        viewModelScope.launch {
            repository.deleteMovieFromFavorites(movie)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: MoviesRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (FavoriteMoviesViewModel(repository) as T)
        }
    }
}