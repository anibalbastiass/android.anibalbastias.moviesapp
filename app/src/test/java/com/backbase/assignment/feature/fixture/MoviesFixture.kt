package com.backbase.assignment.feature.fixture

import com.backbase.assignment.feature.domain.model.DomainMovieDetail
import com.backbase.assignment.feature.domain.model.DomainMovieItem

object MoviesFixture {
    fun createDomainMoviesList() = (0..5).map {
        DomainMovieItem(
            id = 1L,
            posterPath = "",
            originalTitle = "",
            voteAverage = 0.0,
            releaseDate = ""
        )
    }

    fun createDomainMovieDetail() = DomainMovieDetail(
        id = 0,
        posterPath = "",
        originalTitle = "",
        releaseDate = "",
        overview = "",
        genres = arrayListOf()
    )
}