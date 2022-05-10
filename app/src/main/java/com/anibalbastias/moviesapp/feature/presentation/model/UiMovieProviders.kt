package com.anibalbastias.moviesapp.feature.presentation.model

data class UiMovieProviderItem(
    val main: String,
    val buy: List<UiMovieProviderDesc>,
    val rent: List<UiMovieProviderDesc>,
    val flatRate: List<UiMovieProviderDesc>,
)

data class UiMovieProviderDesc(
    val providerId: Int,
    val logoPath: String,
    val providerName: String,
)