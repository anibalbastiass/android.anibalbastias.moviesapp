package com.anibalbastias.moviesapp.feature.presentation.model

data class UiMovieItem(
    var id: Long = 0L,
    val posterPath: String = "",
    val originalTitle: String = "",
    val voteAverage: Double = 0.0,
    val releaseDate: String = "",
    var isFavorite: Boolean = false
)
