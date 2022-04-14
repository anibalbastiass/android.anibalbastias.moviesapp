package com.backbase.assignment.feature.domain.usecase

import androidx.paging.*
import com.backbase.assignment.feature.domain.mediator.MoviesPagingMediator
import com.backbase.assignment.feature.domain.repository.RemoteMoviesRepository
import com.backbase.assignment.feature.presentation.mapper.UiMovieMapper
import com.backbase.assignment.feature.presentation.model.UiMovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
open class GetPagingMoviesUseCase @Inject constructor(
    private val repository: RemoteMoviesRepository,
    private val mapper: UiMovieMapper,
    private val mediator: MoviesPagingMediator,
) {
    fun execute(pagingConfig: PagingConfig): Flow<PagingData<UiMovieItem>> =
        Pager(
            config = pagingConfig,
            pagingSourceFactory = repository::getPopularMovies,
            remoteMediator = mediator
        ).flow.map { pagingData ->
            pagingData.map { movieEntity ->
                with(mapper) {
                    movieEntity.fromEntityToUi()
                }
            }
        }
}