package com.anibalbastias.moviesapp.feature.domain.usecase.local

import com.anibalbastias.moviesapp.feature.domain.repository.LocalMoviesRepository
import com.anibalbastias.moviesapp.feature.presentation.mapper.UiMovieMapper
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class RemoveFavoriteMovieUseCase @Inject constructor(
    private val repository: LocalMoviesRepository,
    private val mapper: UiMovieMapper,
) {
    suspend fun execute(movie: UiMovieItem): Flow<Boolean> =
        repository.removeFavoriteMovie(with(mapper) { movie.fromUiToEntity() })
}