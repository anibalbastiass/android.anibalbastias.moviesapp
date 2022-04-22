package com.anibalbastias.moviesapp.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.domain.UiMovieDataState
import com.anibalbastias.moviesapp.feature.domain.UiMovieDetailDataState
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieDetail
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieItem
import com.anibalbastias.moviesapp.feature.domain.usecase.GetMovieDetailUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.GetNowPlayingMoviesUseCase
import com.anibalbastias.moviesapp.feature.presentation.mapper.UiMovieMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val listUseCase: GetNowPlayingMoviesUseCase,
    private val detailUseCase: GetMovieDetailUseCase,
    private val mapper: UiMovieMapper,
) : ViewModel() {

    private val _nowPlayingMovies = MutableStateFlow<UiMovieDataState>(APIState.Loading)
    val nowPlayingMovies: StateFlow<UiMovieDataState>
        get() = _nowPlayingMovies

    private val _detailMovies = MutableStateFlow<UiMovieDetailDataState>(APIState.Loading)
    val detailMovies: StateFlow<UiMovieDetailDataState>
        get() = _detailMovies

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun getNowPlayingMovies(isRefreshing: Boolean = false) {
        if (isRefreshing) _isRefreshing.value = true

        viewModelScope.launch {
            listUseCase.execute()
                .catch {
                    _nowPlayingMovies.value = APIState.Error("No Internet Connection")
                    hideRefreshing()
                }
                .collect { dataState ->
                    _nowPlayingMovies.value = transformListState(dataState)
                    hideRefreshing()
                }
        }
    }

    private suspend fun hideRefreshing() {
        delay(500)
        _isRefreshing.value = false
    }

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            detailUseCase.execute(movieId)
                .catch {
                    _detailMovies.value = APIState.Error("No Internet Connection")
                }
                .collect { dataState ->
                    _detailMovies.value = transformDetailState(dataState)
                }
        }
    }

    private fun transformDetailState(dataState: APIState<DomainMovieDetail>) = when (dataState) {
        is APIState.Empty -> APIState.Empty(dataState.error)
        is APIState.Error -> APIState.Error(dataState.error)
        APIState.Loading -> APIState.Loading
        is APIState.Success -> with(mapper) { APIState.Success(dataState.data.fromDomainToUi()) }
    }

    private fun transformListState(dataState: APIState<List<DomainMovieItem>>) = when (dataState) {
        is APIState.Empty -> APIState.Empty(dataState.error)
        is APIState.Error -> APIState.Error(dataState.error)
        APIState.Loading -> APIState.Loading
        is APIState.Success -> with(mapper) {
            APIState.Success(
                dataState.data.map {
                    it.fromDomainToUi()
                }
            )
        }
    }
}
