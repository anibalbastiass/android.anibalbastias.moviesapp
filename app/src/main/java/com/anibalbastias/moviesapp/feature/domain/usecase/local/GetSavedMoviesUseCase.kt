package com.anibalbastias.moviesapp.feature.domain.usecase.local

import com.anibalbastias.moviesapp.feature.domain.repository.LocalMoviesRepository
import com.anibalbastias.moviesapp.feature.presentation.mapper.UiMovieMapper
import com.anibalbastias.moviesapp.feature.presentation.model.UiSavedMovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

open class GetSavedMoviesUseCase @Inject constructor(
    private val repository: LocalMoviesRepository,
    private val mapper: UiMovieMapper,
) {
    suspend fun execute(): Flow<List<UiSavedMovieItem>> =
        repository.getSavedMoviesList().map { list ->
            list.map {
                with(mapper) { it.fromEntityToUi() }
            }
        }
}