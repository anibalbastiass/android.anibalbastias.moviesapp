package com.anibalbastias.moviesapp.feature.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.anibalbastias.moviesapp.feature.domain.usecase.local.AddSavedMovieUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.local.ClearAllSavedMoviesUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.local.GetSavedMoviesUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.local.RemoveSavedMovieByIdUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.remote.SearchPagingMoviesUseCase
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import com.anibalbastias.moviesapp.feature.presentation.model.UiSavedMovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchPagingUseCase: SearchPagingMoviesUseCase,
    private val addSavedMovieUseCase: AddSavedMovieUseCase,
    private val clearAllSavedMoviesUseCase: ClearAllSavedMoviesUseCase,
    private val getSavedMoviesUseCase: GetSavedMoviesUseCase,
    private val removeSavedMovieByIdUseCase: RemoveSavedMovieByIdUseCase,
) : ViewModel() {

    private val _searchText: MutableStateFlow<String> = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText

//    private val _savedSearches: MutableStateFlow<List<UiSavedMovieItem>> =
//        MutableStateFlow(listOf())
//    val savedSearches: StateFlow<List<UiSavedMovieItem>> = _savedSearches
    val savedSearches = mutableStateListOf<UiSavedMovieItem>()

    fun searchMovies(query: String): Flow<PagingData<UiMovieItem>> =
        searchPagingUseCase.execute(query).cachedIn(viewModelScope)

    fun updateSearchText(query: String) {
        _searchText.value = query
    }

    fun onClearClick() {
        _searchText.value = ""
    }

    fun addSearchMovie(query: String) {
        val createdAt = Date().time
        viewModelScope.launch {
            addSavedMovieUseCase.execute(
                UiSavedMovieItem(
                    id = "$createdAt",
                    title = query,
                    createdAt = createdAt
                )
            )
                .catch { Log.d("Search Movie", "Created Error") }
                .collect { Log.d("Search Movie", "Created OK") }
        }
    }

    fun clearAllSavedMovies() {
        viewModelScope.launch {
            clearAllSavedMoviesUseCase.execute()
                .catch { Log.d("Search Movie", "Clear Movies Error") }
                .collect { Log.d("Search Movie", "Clear Movies OK") }
        }
    }

    fun getSavedSearchesMovies() {
        viewModelScope.launch {
            getSavedMoviesUseCase.execute()
                .catch { Log.d("Search Movie", "Get Movies Error") }
                .collect { list ->
                    Log.d("Search Movie", "Get Movies OK")
                    savedSearches.clear()
                    savedSearches.addAll(list)
                }
        }
    }

    fun removeSavedSearch(searchId: String) {
        viewModelScope.launch {
            removeSavedMovieByIdUseCase.execute(searchId)
                .catch { Log.d("Search Movie", "Remove Movie $searchId Error") }
                .collect { list ->
                    Log.d("Search Movie", "Remove Movie $searchId OK")
                }
        }
    }
}