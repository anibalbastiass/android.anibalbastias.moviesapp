package com.anibalbastias.moviesapp.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anibalbastias.moviesapp.feature.domain.usecase.remote.SearchPagingMoviesUseCase
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchPagingMoviesUseCase,
) : ViewModel() {

    val searchText: MutableStateFlow<String> = MutableStateFlow("")

    fun searchMovies(query: String): Flow<PagingData<UiMovieItem>> {
        searchText.value = query
        return useCase.execute(query).cachedIn(viewModelScope)
    }

    fun onClearClick() {
        searchText.value = ""
    }
}