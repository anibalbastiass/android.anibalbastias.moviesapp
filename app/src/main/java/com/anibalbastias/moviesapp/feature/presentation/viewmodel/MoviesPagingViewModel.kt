package com.anibalbastias.moviesapp.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.PAGE_SIZE
import com.anibalbastias.moviesapp.feature.domain.usecase.remote.GetPagingMoviesUseCase
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesPagingViewModel @Inject constructor(
    useCase: GetPagingMoviesUseCase,
) : ViewModel() {

    val pagedMovieList: Flow<PagingData<UiMovieItem>> =
        useCase.execute(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            )
        ).cachedIn(viewModelScope)
}
