package com.backbase.assignment.feature.domain.model

data class DomainMovieDetail(
    var id: Int,
    val posterPath: String,
    val originalTitle: String,
    val releaseDate: String,
    val overview: String,
    val genres: List<String>
)
