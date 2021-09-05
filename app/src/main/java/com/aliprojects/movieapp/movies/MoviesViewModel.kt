package com.aliprojects.movieapp.movies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliprojects.movieapp.models.Movie
import com.aliprojects.movieapp.network.MovieApi
import com.aliprojects.movieapp.network.asMovieModel
import kotlinx.coroutines.launch
import java.lang.Exception

class MoviesViewModel: ViewModel() {
    companion object {
        private const val TAG = "MoviesViewModel"
    }
    private val _movies = MutableLiveData<List<Movie>>()
    val movies:LiveData<List<Movie>> = _movies

    init {
        getMovies()
        Log.d(TAG, "Movies: ${_movies.value}")
    }

    private fun getMovies()
    {
        viewModelScope.launch {
            try {
                _movies.value =MovieApi.service.getDiscoverMovie().asMovieModel()
            }catch (e:Exception)
            {
                Log.e(Companion.TAG, "getMovies: ${e.message}")
            }

        }
    }


}