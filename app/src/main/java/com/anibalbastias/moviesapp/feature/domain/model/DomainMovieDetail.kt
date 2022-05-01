package com.anibalbastias.moviesapp.feature.domain.model

data class DomainMovieDetail(
    var id: Int,
    val posterPath: String,
    val backdropPath: String,
    val originalTitle: String,
    val releaseDate: String,
    val overview: String,
    val genres: List<String>
)
