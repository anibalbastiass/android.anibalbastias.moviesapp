package com.anibalbastias.moviesapp.feature.domain.usecase

import androidx.paging.*
import com.anibalbastias.moviesapp.feature.domain.mediator.MoviesPagingMediator
import com.anibalbastias.moviesapp.feature.domain.repository.RemoteMoviesRepository
import com.anibalbastias.moviesapp.feature.presentation.mapper.UiMovieMapper
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
open class GetFavoriteMoviesUseCase @Inject constructor(
    private val repository: RemoteMoviesRepository,
    private val mapper: UiMovieMapper,
    private val mediator: MoviesPagingMediator,
) {
    fun execute(pagingConfig: PagingConfig): Flow<PagingData<UiMovieItem>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = repository::getFavoriteMovies,
            remoteMediator = mediator
        ).flow.map { pagingData ->
            pagingData.map { movieEntity ->
                with(mapper) {
                    movieEntity.fromEntityToUi()
                }
            }
        }
}