package com.anibalbastias.moviesapp.feature.data.remote.mapper

import android.annotation.SuppressLint
import com.anibalbastias.moviesapp.BuildConfig
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.data.remote.model.*
import com.anibalbastias.moviesapp.feature.domain.model.*
import java.text.SimpleDateFormat
import java.util.*

class RemoteMovieItemMapper {

    fun fromRemoteToEntity(
        results: List<RemoteMovieResult>?,
    ): List<EntityMovieItem> {
        return results!!.map { movie ->
            EntityMovieItem(
                id = movie.id.toString(),
                posterPath = movie.posterPath,
                originalTitle = movie.originalTitle ?: "",
                voteAverage = movie.voteAverage ?: 0.0,
                releaseDate = movie.releaseDate,
                isFavorite = true
            )
        }
    }

    fun RemoteMovieResult.fromRemoteToDomain() = DomainMovieItem(
        id = id ?: 0,
        posterPath = posterPath,
        originalTitle = originalTitle ?: "",
        voteAverage = voteAverage ?: 0.0,
        releaseDate = releaseDate,
        isFavorite = false
    )

    fun RemoteMovieDetail.fromRemoteToDomain() = DomainMovieDetail(
        id = id?.toInt() ?: 0,
        posterPath = getUrlImage(posterPath),
        backdropPath = getUrlImage(backdropPath),
        originalTitle = originalTitle ?: "",
        runtime = calculateTime(runtime ?: 0),
        releaseDate = getFormattedDate(releaseDate),
        overview = overview ?: "",
        genres = genres?.map { it.name ?: "" } ?: listOf()
    )

    fun RemoteMovieVideoItem.fromRemoteToDomain() = DomainMovieVideoItem(
        id = id ?: "",
        key = key ?: "",
        name = name ?: "",
        publishedAt = publishedAt ?: "",
        site = site ?: "",
        type = type ?: "",
    )

    fun RemoteMovieCredits.fromRemoteToDomain() = DomainMovieCredits(
        cast = cast?.map { it.fromRemoteToDomain() } ?: listOf(),
        crew = crew?.map { it.fromRemoteToDomain() } ?: listOf(),
    )

    private fun RemoteMovieCastItem.fromRemoteToDomain() = DomainMovieCastItem(
        id = id ?: 0,
        creditId = creditId ?: "",
        department = department ?: "",
        job = job ?: "",
        knownForDepartment = knownForDepartment ?: "",
        name = name ?: "",
        originalName = originalName ?: "",
        popularity = popularity ?: 0.0,
        profilePath = profilePath ?: "",
        castId = castId ?: 0,
        character = character ?: "",
    )

    fun RemoteMovieProviderItem.fromRemoteToDomain(main: String) = DomainMovieProviderItem(
        main = main,
        buy = buy?.map { it.fromRemoteToDomain() } ?: listOf(),
        rent = rent?.map { it.fromRemoteToDomain() } ?: listOf(),
        flatRate = flatRate?.map { it.fromRemoteToDomain() } ?: listOf(),
    )

    private fun RemoteMovieProviderDesc.fromRemoteToDomain() = DomainMovieProviderDesc(
        providerId = providerId ?: 0,
        logoPath = logoPath ?: "",
        providerName = providerName ?: "",
    )

    fun RemoteMovieTranslationItem.fromRemoteToDomain() = DomainMovieTranslationItem(
        translationData = translationData?.fromRemoteToDomain() ?: DomainMovieTranslationData(),
        englishName = englishName ?: "",
        iso31661 = iso31661 ?: "",
        iso6391 = iso6391 ?: "",
        name = name ?: "",
    )

    private fun RemoteMovieTranslationData.fromRemoteToDomain() = DomainMovieTranslationData(
        overview = overview ?: "",
        runtime = runtime ?: 0,
        tagline = tagline ?: "",
        title = title ?: "",
    )

    private fun getUrlImage(suffix: String?) = BuildConfig.IMAGE_URL + suffix

    @SuppressLint("SimpleDateFormat")
    private fun getFormattedDate(rawDate: String?): String {
        return if (rawDate?.isNotEmpty()!!) {
            val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(rawDate)
            SimpleDateFormat("MMMM dd, yyyy").format(date)
        } else {
            rawDate
        }
    }

    private fun calculateTime(time: Int): String {
        val hours = time / 60
        val minutes = time % 60
        return String.format("%d h %02d m", hours, minutes)
    }
}