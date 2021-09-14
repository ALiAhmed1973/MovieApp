package com.aliprojects.movieapp.movies

import androidx.lifecycle.*
import com.aliprojects.movieapp.models.Movie
import com.aliprojects.movieapp.repository.MoviesRepository
import kotlinx.coroutines.launch

class MoviesViewModel(private val repository: MoviesRepository) : ViewModel() {
    companion object {
        private const val TAG = "MoviesViewModel"
    }

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _navigateToMovieScreen = MutableLiveData<Movie?>()
    val navigateToMovieScreen: LiveData<Movie?>
        get() = _navigateToMovieScreen

    init {
        viewModelScope.launch {
            _movies.value = repository.getMoviesFromServer()
        }

    }

//    private fun getMovies()
//    {
//        viewModelScope.launch {
//            try {
//                _movies.value =MovieApi.service.getDiscoverMovie().asMovieModel()
//            }catch (e:Exception)
//            {
//                Log.e(TAG, "getMovies: ${e.message}")
//            }
//
//        }
//    }

    fun navigateToMovieDetails(movie: Movie) {
        _navigateToMovieScreen.value = movie
    }

    fun navigationCompleted() {
        _navigateToMovieScreen.value = null
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: MoviesRepository) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return (MoviesViewModel(repository) as T)
        }
    }
}