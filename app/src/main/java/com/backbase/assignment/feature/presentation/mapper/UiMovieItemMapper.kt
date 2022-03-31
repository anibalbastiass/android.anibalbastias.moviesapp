package com.backbase.assignment.feature.presentation.mapper

import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.domain.model.DomainMovieItem
import com.backbase.assignment.feature.presentation.model.UiMovieItem

class UiMovieItemMapper {
    fun DomainMovieItem.fromDomainToUi() = UiMovieItem(
        id = id,
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate
    )

    fun EntityMovieItem.fromDomainToUi() = UiMovieItem(
        index = index,
        id = movieId,
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate
    )
}