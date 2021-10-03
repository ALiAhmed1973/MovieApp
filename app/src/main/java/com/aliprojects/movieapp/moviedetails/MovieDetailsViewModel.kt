package com.aliprojects.movieapp.moviedetails

import android.util.Log
import androidx.lifecycle.*
import com.aliprojects.movieapp.models.Movie
import com.aliprojects.movieapp.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MovieDetailsViewModel"

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie
    lateinit var favMovie: LiveData<Movie?>


    fun getFavMovie(movie: Movie) {
        _movie.value = movie
        favMovie = repository.getFavoriteMovieByID(movie.id)

    }

    fun setMovieDetails(movie: Movie?) {
        _movie.value = movie ?: _movie.value?.copy(isFavorite = false)
    }

    fun addOrRemoveMovieFromFavorite() {
        viewModelScope.launch {
                Log.d(TAG, "addOrRemoveMovieFromFavorite: ${_movie.value}")
                _movie.value?.let {
                    if (it.isFavorite) {
                        repository.deleteMovieFromFavorites(it)
                    } else {
                        it.isFavorite = true
                        repository.insertMovieToFavorite(it)
                    }
                }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: MoviesRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (MovieDetailsViewModel(repository) as T)
        }
    }
}