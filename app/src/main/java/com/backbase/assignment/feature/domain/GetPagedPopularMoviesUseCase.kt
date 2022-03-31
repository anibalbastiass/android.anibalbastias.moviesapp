package com.backbase.assignment.feature.domain

import androidx.paging.DataSource
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.data.remote.RemoteMoviesRepositoryImpl
import javax.inject.Inject

open class GetPagedPopularMoviesUseCase @Inject constructor(
    private val repository: RemoteMoviesRepositoryImpl
) {
    fun execute(): DataSource.Factory<Int, EntityMovieItem> =
        repository.getPopularMovies()
}