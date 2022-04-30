package com.anibalbastias.moviesapp.feature.presentation.mapper

import com.anibalbastias.moviesapp.feature.data.local.model.EntityFavoriteMovieItem
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.data.local.model.EntitySavedMovieItem
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieDetail
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieItem
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import com.anibalbastias.moviesapp.feature.presentation.model.UiSavedMovieItem

class UiMovieMapper {

    fun DomainMovieItem.fromDomainToUi() = UiMovieItem(
        id = id,
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        isFavorite = isFavorite
    )

    fun EntityMovieItem.fromEntityToUi(favorites: List<EntityFavoriteMovieItem>): UiMovieItem {
        return UiMovieItem(
            id = id.toLong(),
            posterPath = posterPath,
            originalTitle = originalTitle,
            voteAverage = voteAverage,
            releaseDate = releaseDate,
            isFavorite = favorites.find { it.id == id } != null
        )
    }

    fun EntityFavoriteMovieItem.fromEntityToUi() = UiMovieItem(
        id = id.toLong(),
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
        isFavorite = true
    )

    fun UiMovieItem.fromUiToEntity() = EntityFavoriteMovieItem(
        id = id.toString(),
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate,
    )

    fun DomainMovieDetail.fromDomainToUi() = UiMovieDetail(
        id = id,
        posterPath = posterPath,
        originalTitle = originalTitle,
        releaseDate = releaseDate,
        overview = overview,
        genres = genres
    )

    fun EntitySavedMovieItem.fromEntityToUi() = UiSavedMovieItem(
        id = id,
        title = title,
        createdAt = createdAt
    )

    fun UiSavedMovieItem.fromUiToEntity() = EntitySavedMovieItem(
        id = id,
        title = title,
        createdAt = createdAt
    )
}