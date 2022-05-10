package com.anibalbastias.moviesapp.feature.domain.model

data class DomainMovieTranslationItem(
    val translationData: DomainMovieTranslationData,
    val englishName: String,
    val iso31661: String,
    val iso6391: String,
    val name: String,
)

data class DomainMovieTranslationData(
    val overview: String = "",
    val runtime: Int = 0,
    val tagline: String = "",
    val title: String = "",
)