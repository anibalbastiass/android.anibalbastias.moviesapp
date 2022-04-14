package com.backbase.assignment.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.PAGE_SIZE
import com.backbase.assignment.feature.domain.usecase.GetPagingMoviesUseCase
import com.backbase.assignment.feature.presentation.model.UiMovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
@ExperimentalPagingApi
class MoviesPagingViewModel @Inject constructor(
    useCase: GetPagingMoviesUseCase
) : ViewModel() {

    val pagedMovieList: Flow<PagingData<UiMovieItem>> =
        useCase.execute(
            PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = true
            )
        ).cachedIn(viewModelScope)
}
