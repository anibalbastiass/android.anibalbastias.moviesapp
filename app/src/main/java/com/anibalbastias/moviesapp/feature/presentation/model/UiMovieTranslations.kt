package com.anibalbastias.moviesapp.feature.presentation.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class UiMovieTranslationItem(
    val translationData: UiMovieTranslationData,
    val englishName: String,
    val iso31661: String,
    val iso6391: String,
    val name: String,
) {
    var isSelected by mutableStateOf(false)
}

data class UiMovieTranslationData(
    val overview: String = "",
    val runtime: Int = 0,
    val tagline: String = "",
    val title: String = "",
)