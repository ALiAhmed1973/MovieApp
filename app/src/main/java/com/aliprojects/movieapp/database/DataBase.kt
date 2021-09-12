package com.aliprojects.movieapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.aliprojects.movieapp.models.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM favorites")
    fun getMovies(): LiveData<List<DatabaseMovie>>

    @Insert
    fun insertMovie(movie: DatabaseMovie)

    @Delete
    fun deleteMovie(movie: DatabaseMovie)
}

@Database(entities = [DatabaseMovie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}

private lateinit var INSTANCE: MoviesDatabase

fun getDatabase(context: Context): MoviesDatabase {
    if (!::INSTANCE.isInitialized) {
        synchronized(MoviesDatabase::class.java)
        {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                MoviesDatabase::class.java,
                "movies"
            ).build()
        }
    }
    return INSTANCE
}