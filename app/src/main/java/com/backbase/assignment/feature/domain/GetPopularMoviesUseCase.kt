package com.backbase.assignment.feature.domain

import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.data.remote.RemoteMoviesRepositoryImpl
import javax.inject.Inject

open class GetPopularMoviesUseCase @Inject constructor(
    private val repository: RemoteMoviesRepositoryImpl
) {
    suspend fun execute(
        pageToLoad: Int,
        pageSize: Int
    ): List<EntityMovieItem> =
        repository.loadPageOfMovies(pageToLoad, pageSize)
}