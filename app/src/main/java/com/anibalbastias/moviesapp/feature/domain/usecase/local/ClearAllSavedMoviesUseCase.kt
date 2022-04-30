package com.anibalbastias.moviesapp.feature.domain.usecase.local

import com.anibalbastias.moviesapp.feature.domain.repository.LocalMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class ClearAllSavedMoviesUseCase @Inject constructor(
    private val repository: LocalMoviesRepository,
) {
    suspend fun execute(): Flow<Boolean> = repository.clearAllSavedMovies()
}