package com.anibalbastias.moviesapp.feature.domain.model

data class DomainMovieCredits(
    val cast: List<DomainMovieCastItem>,
    val crew: List<DomainMovieCastItem>,
)

data class DomainMovieCastItem(
    val id: Int,
    val creditId: String,
    val department: String,
    val job: String,
    val knownForDepartment: String,
    val name: String,
    val originalName: String,
    val popularity: Double,
    val profilePath: String,
    val castId: Int,
    val character: String
)
