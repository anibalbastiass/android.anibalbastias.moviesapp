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
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: SearchPagingMoviesUseCase,
) : ViewModel() {

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

    fun searchMovies(query: String): Flow<PagingData<UiMovieItem>> =
        useCase.execute(query).cachedIn(viewModelScope)

    fun updateSearchText(query: String) {
        _searchText.value = query
    }

    fun onClearClick() {
        _searchText.value = ""
    }
}