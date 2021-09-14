package com.aliprojects.movieapp.moviedetails

import androidx.lifecycle.*
import com.aliprojects.movieapp.models.Movie
import com.aliprojects.movieapp.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MovieDetailsViewModel"

class MovieDetailsViewModel(private val repository: MoviesRepository) : ViewModel() {
    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie
    val favMovies = repository.favMovies

    fun setMovieDetails(movie: Movie, favMovies: List<Movie>) {
        favMovies.forEach {
            if (it.id == movie.id) {
                movie.isFavorite = true
                _movie.value = movie
                return
            }
        }
        movie.isFavorite = false
        _movie.value = movie
    }

    fun addOrRemoveMovieFromFavorite() {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                _movie.value?.let {
                    if(it.isFavorite)
                    {
                        repository.deleteMovieFromFavorites(it)
                    }else
                    {
                        it.isFavorite = true
                        repository.insertMovieToFavorite(it)
                    }

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