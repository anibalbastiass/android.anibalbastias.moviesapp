package com.anibalbastias.moviesapp.feature.presentation.model

import androidx.compose.runtime.mutableStateOf

data class UiMovieDetail(
    var id: Int,
    val posterPath: String,
    val backdropPath: String,
    val _originalTitle: String,
    val runtime: String,
    val releaseDate: String,
    val _overview: String,
    val genres: List<String>,
    val videos: List<UiMovieVideoItem>,
    val credits: UiMovieCredits,
    val providers: List<UiMovieProviderItem>,
    val similar: List<UiMovieItem>,
    val translations: List<UiMovieTranslationItem>,
) {
    val originalTitle = mutableStateOf(_originalTitle)
    val overview = mutableStateOf(_overview)
}

