package com.anibalbastias.moviesapp.feature.data.remote.mapper

import android.annotation.SuppressLint
import com.anibalbastias.moviesapp.BuildConfig
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteMovieDetail
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteMovieResult
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieDetail
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieItem
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