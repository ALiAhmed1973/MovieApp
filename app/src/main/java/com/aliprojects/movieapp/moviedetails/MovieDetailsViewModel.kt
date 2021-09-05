package com.aliprojects.movieapp.moviedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aliprojects.movieapp.models.Movie

class MovieDetailsViewModel : ViewModel() {
private val _movie = MutableLiveData<Movie>()
    val movie:LiveData<Movie>
    get() = _movie



    fun setMovieDetails(movie:Movie)
    {
        _movie.value=movie
    }
}