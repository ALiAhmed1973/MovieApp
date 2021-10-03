package com.aliprojects.movieapp.network

import com.aliprojects.movieapp.models.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//For Result object ResponseData
@JsonClass(generateAdapter = true)
data class NetworkMovieContainer(@Json(name = "results") val moviesList: List<NetworkMovie>)

//Arrays of movies in Result object
@JsonClass(generateAdapter = true)
data class NetworkMovie(
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name="backdrop_path")
    val backdropPath:String?,
    @Json(name = "overview")
    val overView: String,
    @Json(name = "release_date")
    val releaseDate: String,
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "vote_average")
    val voteAverage: Double
)

fun NetworkMovieContainer.asMovieModel():List<Movie>
{
    return moviesList.map{
        Movie(
            id=it.id,
            title = it.title,
            posterPath = it.posterPath,
            backdropPath= it.backdropPath,
            overView = it.overView,
            releaseDate = it.releaseDate,
            voteCount = it.voteCount,
            voteAverage = (it.voteAverage/2).toFloat()
        )
    }
}
