package com.anibalbastias.moviesapp.feature.presentation.model

data class UiMovieDetail(
    var id: Int,
    val posterPath: String,
    val backdropPath: String,
    val originalTitle: String,
    val runtime: String,
    val releaseDate: String,
    val overview: String,
    val genres: List<String>,
)
