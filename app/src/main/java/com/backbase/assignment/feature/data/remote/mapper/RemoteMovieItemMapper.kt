package com.backbase.assignment.feature.data.remote.mapper

import android.annotation.SuppressLint
import com.backbase.assignment.BuildConfig
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.PAGE_SIZE
import com.backbase.assignment.feature.data.remote.model.RemoteMovieData
import com.backbase.assignment.feature.data.remote.model.RemoteMovieDetail
import com.backbase.assignment.feature.data.remote.model.RemoteMovieResult
import com.backbase.assignment.feature.domain.model.DomainMovieDetail
import com.backbase.assignment.feature.domain.model.DomainMovieItem
import java.text.SimpleDateFormat
import java.util.*

class RemoteMovieItemMapper {

    fun fromRemoteToEntity(response: RemoteMovieData): List<EntityMovieItem> {
        val firstIndex = (response.page!! - 1) * PAGE_SIZE
        return response.results!!.mapIndexed { index, productResponse ->
            fromRemoteToEntity(productResponse, (firstIndex + index).toLong())
        }.toList()
    }

    private fun fromRemoteToEntity(movie: RemoteMovieResult, index: Long): EntityMovieItem {
        return EntityMovieItem(
            index = index,
            movieId = movie.id ?: 0,
            posterPath = movie.posterPath,
            originalTitle = movie.originalTitle ?: "",
            voteAverage = movie.voteAverage ?: 0.0,
            releaseDate = movie.releaseDate ?: ""
        )
    }

    fun RemoteMovieResult.fromRemoteToDomain() = DomainMovieItem(
        id = id ?: 0,
        posterPath = posterPath,
        originalTitle = originalTitle ?: "",
        voteAverage = voteAverage ?: 0.0,
        releaseDate = releaseDate
    )

    fun RemoteMovieDetail.fromRemoteToDomain() = DomainMovieDetail(
        id = id?.toInt() ?: 0,
        posterPath = getUrlImage(posterPath),
        originalTitle = originalTitle ?: "",
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
}