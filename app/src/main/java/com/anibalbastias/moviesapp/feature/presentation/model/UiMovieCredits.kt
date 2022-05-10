package com.anibalbastias.moviesapp.feature.presentation.model

data class UiMovieCredits(
    val cast: List<UiMovieCastItem> = listOf(),
    val crew: List<UiMovieCastItem> = listOf(),
)

data class UiMovieCastItem(
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
    val character: String,
)
