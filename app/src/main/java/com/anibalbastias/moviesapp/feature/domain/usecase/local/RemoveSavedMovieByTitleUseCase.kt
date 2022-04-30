package com.anibalbastias.moviesapp.feature.domain.usecase.local

import com.anibalbastias.moviesapp.feature.domain.repository.LocalMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class RemoveSavedMovieByTitleUseCase @Inject constructor(
    private val repository: LocalMoviesRepository
) {
    suspend fun execute(id: String): Flow<Boolean> = repository.deleteSavedMovieByTitle(id)
}