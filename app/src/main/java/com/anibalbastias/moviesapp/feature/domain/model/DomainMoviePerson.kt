package com.anibalbastias.moviesapp.feature.domain.model

data class DomainMoviePerson(
    val id: Int,
    val biography: String,
    val birthday: String,
    val deathDay: String,
    val gender: String,
    val knownForDepartment: String,
    val name: String,
    val placeOfBirth: String,
    val profilePath: String,
    val knownFor: List<DomainMovieItem>,
    val acting: List<DomainMovieItem>,
    val writing: List<DomainMovieItem>,
    val directing: List<DomainMovieItem>,
    val production: List<DomainMovieItem>,
    val creator: List<DomainMovieItem>,
)