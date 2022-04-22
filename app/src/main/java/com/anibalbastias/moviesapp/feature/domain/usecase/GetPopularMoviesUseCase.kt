package com.anibalbastias.moviesapp.feature.domain.usecase

import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesRepositoryImpl
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