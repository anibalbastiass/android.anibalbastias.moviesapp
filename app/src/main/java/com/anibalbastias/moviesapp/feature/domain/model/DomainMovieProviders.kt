package com.anibalbastias.moviesapp.feature.domain.model

data class DomainMovieProviderItem(
    val main: String,
    val buy: List<DomainMovieProviderDesc>,
    val rent: List<DomainMovieProviderDesc>,
    val flatRate: List<DomainMovieProviderDesc>,
)

data class DomainMovieProviderDesc(
    val providerId: Int,
    val logoPath: String,
    val providerName: String,
)