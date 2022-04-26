package com.anibalbastias.moviesapp.feature.presentation.mapper

import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieDetail
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieItem
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem

class UiMovieMapper {
    fun UiMovieItem.fromUiToDomain() = EntityMovieItem(
        id = id.toString(),
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        isFavorite = isFavorite,
    )

    fun DomainMovieItem.fromDomainToUi() = UiMovieItem(
        id = id,
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        isFavorite = isFavorite,
    )

    fun EntityMovieItem.fromEntityToUi() = UiMovieItem(
        id = id.toLong(),
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        isFavorite = isFavorite
    )

    fun DomainMovieDetail.fromDomainToUi() = UiMovieDetail(
        id = id,
        posterPath = posterPath,
        originalTitle = originalTitle,
        releaseDate = releaseDate,
        overview = overview,
        genres = genres
    )
}