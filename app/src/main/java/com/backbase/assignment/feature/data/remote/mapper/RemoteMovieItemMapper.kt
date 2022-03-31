package com.backbase.assignment.feature.data.remote.mapper

import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.data.remote.model.RemoteMovieData
import com.backbase.assignment.feature.data.remote.model.RemoteMovieResult
import com.backbase.assignment.feature.domain.model.DomainMovieItem

class RemoteMovieItemMapper {

    companion object {
        // Defined by API
        private const val PAGE_SIZE = 20
    }

    fun fromRemoteToEntity(response: RemoteMovieData): List<EntityMovieItem> {
        val firstIndex = (response.page - 1) * PAGE_SIZE
        return response.results.mapIndexed { index, productResponse ->
            fromRemoteToEntity(productResponse, (firstIndex + index).toLong())
        }.toList()
    }

    private fun fromRemoteToEntity(movie: RemoteMovieResult, index: Long): EntityMovieItem {
        return EntityMovieItem(
            index = index,
            posterPath = movie.posterPath,
            originalTitle = movie.originalTitle,
            voteAverage = movie.voteAverage,
            releaseDate = movie.releaseDate
        )
    }

    fun RemoteMovieResult.fromRemoteToDomain() = DomainMovieItem(
        id = id,
        posterPath = posterPath,
        originalTitle = originalTitle,
        voteAverage = voteAverage,
        releaseDate = releaseDate
    )
}