package com.aliprojects.movieapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aliprojects.movieapp.models.Movie

@Entity(tableName = "favorites")
data class DatabaseMovie(
    @PrimaryKey val id: Int,

    @ColumnInfo(name = "movie_title") val title: String,

    @ColumnInfo(name = "poster_path") val posterPath: String?,

    @ColumnInfo(name = "backdrop_path") val backdropPath: String?,

    @ColumnInfo(name = "movie_overview") val overView: String,

    @ColumnInfo(name = "release_date") val releaseDate: String,

    @ColumnInfo(name = "vote_count") val voteCount: Int,

    @ColumnInfo(name = "vote_average") val voteAverage: Double,

    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean = false
)

fun List<DatabaseMovie>.asMovieModel(): List<Movie> {

    return map {
        Movie(
            id = it.id,
            title = it.title,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            overView = it.overView,
            releaseDate = it.releaseDate,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage,
            isFavorite = it.isFavorite
        )
    }
}

fun Movie.asDatabaseMovie(): DatabaseMovie {
    return DatabaseMovie(
        id = id,
        title =title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overView = overView,
        releaseDate = releaseDate,
        voteCount = voteCount,
        voteAverage = voteAverage,
        isFavorite = isFavorite
    )
}

fun DatabaseMovie.asMovieModel(): Movie {
    return Movie(
        id = id,
        title =title,
        posterPath = posterPath,
        backdropPath = backdropPath,
        overView = overView,
        releaseDate = releaseDate,
        voteCount = voteCount,
        voteAverage = voteAverage,
        isFavorite = isFavorite
    )
}