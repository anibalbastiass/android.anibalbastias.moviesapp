package com.backbase.assignment.feature.domain.model

data class DomainMovieItem(
    var id: Long,
    val posterPath: String,
    val originalTitle: String,
    val voteAverage: Double,
    val releaseDate: String
)
