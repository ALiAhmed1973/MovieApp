package com.aliprojects.movieapp.models


data class Movie(
    val id: Int,

    val title: String,

    val posterPath: String?,

    val backdropPath:String?,

    val overView: String,

    val releaseDate: String,

    val voteCount: Int,

    val voteAverage: Double
) {
}