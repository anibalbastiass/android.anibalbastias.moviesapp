package com.backbase.assignment.feature.presentation.model

data class UiMovieItem(
    var id: Long,
    val posterPath: String,
    val originalTitle: String,
    val voteAverage: Double,
    val releaseDate: String,
)
