package com.aliprojects.movieapp.network

data class NetworkMovie(
    val id: Int,
    val title: String,
    val posterPath: String?,
    val overView: String,
    val releaseDate: String,
    val voteCount: Int,
    val voteAverage: Double
)
