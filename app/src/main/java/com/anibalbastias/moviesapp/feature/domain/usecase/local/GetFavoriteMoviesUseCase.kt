package com.anibalbastias.moviesapp.feature.domain.usecase.local

import com.anibalbastias.moviesapp.feature.domain.repository.LocalMoviesRepository
import com.anibalbastias.moviesapp.feature.presentation.mapper.UiMovieMapper
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

open class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: LocalMoviesRepository,
    private val mapper: UiMovieMapper,
) {
    fun execute(): Flow<List<UiMovieItem>> =
        repository.getFavoriteMovies()
            .map { list ->
                with(mapper) {
                    list.map { item ->
                        item.fromEntityToUi()
                    }
                }
            }
}