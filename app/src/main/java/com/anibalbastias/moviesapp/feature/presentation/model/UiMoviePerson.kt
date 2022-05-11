package com.anibalbastias.moviesapp.feature.presentation.model

data class UiMoviePerson(
    val id: Int,
    val biography: String,
    val birthday: String,
    val deathDay: String,
    val gender: String,
    val knownForDepartment: String,
    val name: String,
    val placeOfBirth: String,
    val profilePath: String,
    val knownFor: List<UiMovieItem>,
    val acting: List<UiMovieItem>,
    val writing: List<UiMovieItem>,
    val directing: List<UiMovieItem>,
    val production: List<UiMovieItem>,
    val creator: List<UiMovieItem>,
)