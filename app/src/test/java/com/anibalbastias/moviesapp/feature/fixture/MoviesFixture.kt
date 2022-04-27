package com.anibalbastias.moviesapp.feature.fixture

import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieDetail
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieItem

object MoviesFixture {
    fun createDomainMoviesList() = (0..5).map {
        DomainMovieItem(
            id = 1L,
            posterPath = "",
            originalTitle = "",
            voteAverage = 0.0,
            releaseDate = "",
            isFavorite = true
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