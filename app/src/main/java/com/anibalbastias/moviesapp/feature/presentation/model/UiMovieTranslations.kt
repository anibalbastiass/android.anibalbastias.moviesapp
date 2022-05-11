package com.anibalbastias.moviesapp.feature.presentation.model

import androidx.compose.runtime.mutableStateOf

data class UiMovieTranslationItem(
    val translationData: UiMovieTranslationData,
    val englishName: String,
    val iso31661: String,
    val iso6391: String,
    val name: String,
) {
    val isSelected = mutableStateOf(false)
}

data class UiMovieTranslationData(
    val overview: String = "",
    val runtime: Int = 0,
    val tagline: String = "",
    val title: String = "",
)