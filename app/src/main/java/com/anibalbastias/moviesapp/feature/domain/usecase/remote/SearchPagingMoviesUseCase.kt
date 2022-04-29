package com.anibalbastias.moviesapp.feature.domain.usecase.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.PAGE_SIZE
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieItem
import com.anibalbastias.moviesapp.feature.domain.paging.SearchMoviesPagingSource
import com.anibalbastias.moviesapp.feature.presentation.mapper.UiMovieMapper
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchPagingMoviesUseCase @Inject constructor(
    private val pagingSource: SearchMoviesPagingSource,
    private val mapper: UiMovieMapper,
) {

    fun execute(query: String): Flow<PagingData<UiMovieItem>> {
        pagingSource.query = query

        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pagingSource }
        ).flow.map { pagingData ->
            pagingData.map { movieDomain ->
                with(mapper) {
                    movieDomain.fromDomainToUi()
                }
            }
        }
    }
}