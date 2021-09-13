package com.aliprojects.movieapp.moviedetails

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliprojects.movieapp.database.MoviesDatabase
import com.aliprojects.movieapp.database.asDatabaseMovie
import com.aliprojects.movieapp.models.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MovieDetailsViewModel"
class MovieDetailsViewModel : ViewModel() {
private val _movie = MutableLiveData<Movie>()
    val movie:LiveData<Movie>
    get() = _movie



    fun setMovieDetails(movie:Movie)
    {
        _movie.value=movie
    }

//    fun insertMovieToFavorite(database: MoviesDatabase)
//    {
//        viewModelScope.launch{
//            withContext(Dispatchers.IO)
//            {
//                try {
//                    _movie.value?.let { database.movieDao.insertMovie(it.asDatabaseMovie()) }
//                    Log.d(TAG, "insertMovieToFavorite: Success")
//                }catch (e:Exception)
//                {
//                    Log.e(TAG, "insertMovieToFavorite: ${e.message}" )
//                }
//            }
//
//
//        }
//    }
}