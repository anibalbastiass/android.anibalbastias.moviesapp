package com.anibalbastias.moviesapp.feature.domain.model

data class DomainMovieDetail(
    var id: Int,
    val posterPath: String,
    val backdropPath: String,
    val originalTitle: String,
    val releaseDate: String,
    val runtime: String,
    val overview: String,
    val genres: List<String>,
    val videos: List<DomainMovieVideoItem>,
    val credits: DomainMovieCredits,
    val providers: List<DomainMovieProviderItem>,
    val similar: List<DomainMovieItem>,
    val translations: List<DomainMovieTranslationItem>,
)
