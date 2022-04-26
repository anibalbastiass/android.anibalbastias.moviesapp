package com.anibalbastias.moviesapp.feature.domain.usecase

import com.anibalbastias.moviesapp.feature.domain.repository.RemoteMoviesRepository
import com.anibalbastias.moviesapp.feature.presentation.mapper.UiMovieMapper
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class UpdateMovieUseCase @Inject constructor(
    private val repository: RemoteMoviesRepository,
    private val mapper: UiMovieMapper,
) {
    fun execute(movie: UiMovieItem): Flow<Boolean> =
        repository.updateMovie(with(mapper) { movie.fromUiToDomain() })
}