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
    val videos: List<UiMovieVideoItem>,
    val credits: UiMovieCredits,
    val providers: List<UiMovieProviderItem>,
    val similar: List<UiMovieItem>,
    val translations: List<UiMovieTranslationItem>
)

