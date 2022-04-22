package com.anibalbastias.moviesapp.feature.domain.usecase

import androidx.paging.DataSource
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesRepositoryImpl
import javax.inject.Inject

open class GetPagedPopularMoviesUseCase @Inject constructor(
    private val repository: RemoteMoviesRepositoryImpl
) {
    fun execute(): DataSource.Factory<Int, EntityMovieItem> =
        repository.getPopularMovies()
}