package com.aliprojects.movieapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.aliprojects.movieapp.database.MoviesDatabase
import com.aliprojects.movieapp.database.asDatabaseMovie
import com.aliprojects.movieapp.database.asMovieModel
import com.aliprojects.movieapp.models.Movie
import com.aliprojects.movieapp.network.MovieApi
import com.aliprojects.movieapp.network.asMovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Exception

private const val TAG = "MoviesRepository"

class MoviesRepository(private val database: MoviesDatabase) {
    val favMovies:LiveData<List<Movie>> by lazy{ getFavoriteMovies()}
    suspend fun getMoviesFromServer(): List<Movie> {
        return try {
            MovieApi.service.getDiscoverMovie().asMovieModel()
        } catch (e: Exception) {
            Log.e(TAG, "getMovies: ${e.message}")
            emptyList()
        }
    }

     fun getFavoriteMovies():LiveData<List<Movie>>{
         return Transformations.map(database.movieDao.getMovies()){
             it.asMovieModel()
         }
    }

    suspend fun insertMovieToFavorite(movie: Movie) {
        withContext(Dispatchers.IO)
        {
            try {
                database.movieDao.insertMovie(movie.asDatabaseMovie())
            } catch (e: Exception) {
                Log.e(TAG, "insertMovieToFavorite: ${e.message}")
            }

        }

    }

    suspend fun deleteMovieFromFavorites(movie:Movie)
    {
        withContext(Dispatchers.IO)
        {
            try {
                database.movieDao.deleteMovie(movie.asDatabaseMovie())
            }catch (e:Exception)
            {
                Log.e(TAG, "deleteMovieFromFavorites: ${e.message}")
            }

        }
    }
}