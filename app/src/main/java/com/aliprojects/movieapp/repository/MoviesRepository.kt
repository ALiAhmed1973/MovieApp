package com.aliprojects.movieapp.repository

import android.util.Log
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

    suspend fun getMoviesFromServer(): List<Movie> {
        return try {
            MovieApi.service.getDiscoverMovie().asMovieModel()
        } catch (e: Exception) {
            Log.e(TAG, "getMovies: ${e.message}")
            emptyList()
        }
    }

    fun getFavoriteMovies(): List<Movie> {
        return try {
            database.movieDao.getMovies().value?.asMovieModel() ?: emptyList()
        } catch (e: Exception) {
            Log.e(TAG, "getFavoriteMovies: ${e.message}")
            emptyList()
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
}