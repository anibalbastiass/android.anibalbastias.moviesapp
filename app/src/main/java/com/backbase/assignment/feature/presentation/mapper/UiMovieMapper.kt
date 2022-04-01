package com.backbase.assignment.feature.presentation.mapper

import com.backbase.assignment.feature.domain.model.DomainMovieDetail
import com.backbase.assignment.feature.domain.model.DomainMovieItem
import com.backbase.assignment.feature.presentation.model.UiMovieDetail
import com.backbase.assignment.feature.presentation.model.UiMovieItem

class UiMovieMapper {
    fun DomainMovieItem.fromDomainToUi() = UiMovieItem(
        id = id,
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate
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