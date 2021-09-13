package com.aliprojects.movieapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,

    val title: String,

    val posterPath: String?,

    val backdropPath: String?,

    val overView: String,

    val releaseDate: String,

    val voteCount: Int,

    val voteAverage: Double,

    var isFavorite: Boolean = false
) : Parcelable {
}