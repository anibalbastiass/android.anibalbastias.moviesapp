package com.anibalbastias.moviesapp.feature.presentation.mapper

import com.anibalbastias.moviesapp.feature.data.local.model.EntityFavoriteMovieItem
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.data.local.model.EntitySavedMovieItem
import com.anibalbastias.moviesapp.feature.domain.model.*
import com.anibalbastias.moviesapp.feature.presentation.model.*
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeUtils.getYouTubeThumbnail

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

    fun DomainMoviePerson.fromDomainToUi() = UiMoviePerson(
        id = id,
        biography = biography,
        birthday = birthday,
        deathDay = deathDay,
        gender = gender,
        knownForDepartment = knownForDepartment,
        name = name,
        placeOfBirth = placeOfBirth,
        profilePath = profilePath,
        knownFor = knownFor.map { it.fromDomainToUi() },
        acting = acting.map { it.fromDomainToUi() },
        writing = writing.map { it.fromDomainToUi() },
        directing = directing.map { it.fromDomainToUi() },
        production = production.map { it.fromDomainToUi() },
        creator = creator.map { it.fromDomainToUi() },
    )

    fun DomainMovieDetail.fromDomainToUi() = UiMovieDetail(
        id = id,
        posterPath = posterPath,
        backdropPath = backdropPath,
        _originalTitle = originalTitle,
        runtime = runtime,
        releaseDate = releaseDate,
        _overview = overview,
        genres = genres,
        videos = videos.map { it.fromDomainToUi() },
        credits = credits.fromDomainToUi(),
        providers = providers.map { it.fromDomainToUi() },
        similar = similar.map { it.fromDomainToUi() },
        translations = translations.map { it.fromDomainToUi() }
    )

    private fun DomainMovieCredits.fromDomainToUi() = UiMovieCredits(
        cast = cast.map { it.fromDomainToUi() },
        crew = crew.map { it.fromDomainToUi() },
    )

    private fun DomainMovieCastItem.fromDomainToUi() = UiMovieCastItem(
        id = id,
        name = name,
        job = job,
        creditId = creditId,
        department = department,
        knownForDepartment = knownForDepartment,
        originalName = originalName,
        popularity = popularity,
        profilePath = profilePath,
        castId = castId,
        character = character
    )

    private fun DomainMovieProviderItem.fromDomainToUi() = UiMovieProviderItem(
        main = main,
        buy = buy.map { it.fromDomainToUi() },
        rent = rent.map { it.fromDomainToUi() },
        flatRate = flatRate.map { it.fromDomainToUi() },
    )

    private fun DomainMovieProviderDesc.fromDomainToUi() = UiMovieProviderDesc(
        providerId = providerId,
        logoPath = logoPath,
        providerName = providerName,
    )

    private fun DomainMovieTranslationItem.fromDomainToUi() = UiMovieTranslationItem(
        translationData = translationData.fromDomainToUi(),
        englishName = englishName,
        iso31661 = iso31661,
        iso6391 = iso6391,
        name = name,
    )

    private fun DomainMovieTranslationData.fromDomainToUi() = UiMovieTranslationData(
        overview = overview,
        runtime = runtime,
        tagline = tagline,
        title = title,
    )

    fun EntitySavedMovieItem.fromEntityToUi() = UiSavedMovieItem(
        title = title,
        createdAt = createdAt
    )

    fun UiSavedMovieItem.fromUiToEntity() = EntitySavedMovieItem(
        title = title,
        createdAt = createdAt
    )

    private fun DomainMovieVideoItem.fromDomainToUi() = UiMovieVideoItem(
        id = id,
        key = key,
        name = name,
        publishedAt = publishedAt,
        site = site,
        type = type,
        thumbnail = getYouTubeThumbnail(key)
    )
}