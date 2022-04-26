package com.anibalbastias.moviesapp.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.PAGE_SIZE
import com.anibalbastias.moviesapp.feature.domain.usecase.GetFavoriteMoviesUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.GetPagingMoviesUseCase
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class MoviesPagingViewModel @Inject constructor(
    useCase: GetPagingMoviesUseCase,
    favoriteUseCase: GetFavoriteMoviesUseCase
) : ViewModel() {

    val pagedMovieList: Flow<PagingData<UiMovieItem>> =
        useCase.execute(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            )
        ).cachedIn(viewModelScope)

    val pagedFavoriteMovieList: Flow<PagingData<UiMovieItem>> =
        favoriteUseCase.execute(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            )
        ).cachedIn(viewModelScope)
}
