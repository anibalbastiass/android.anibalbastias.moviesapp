package com.backbase.assignment.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.GetMovieDetailUseCase
import com.backbase.assignment.feature.domain.GetNowPlayingMoviesUseCase
import com.backbase.assignment.feature.domain.UiMovieDataState
import com.backbase.assignment.feature.domain.UiMovieDetailDataState
import com.backbase.assignment.feature.domain.model.DomainMovieDetail
import com.backbase.assignment.feature.domain.model.DomainMovieItem
import com.backbase.assignment.feature.presentation.mapper.UiMovieMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val listUseCase: GetNowPlayingMoviesUseCase,
    private val detailUseCase: GetMovieDetailUseCase,
    private val mapper: UiMovieMapper,
) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<UiMovieDataState>()
    val nowPlayingMovies: LiveData<UiMovieDataState>
        get() = _nowPlayingMovies

    private val _detailMovies = MutableLiveData<UiMovieDetailDataState>()
    val detailMovies: LiveData<UiMovieDetailDataState>
        get() = _detailMovies

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            listUseCase.execute()
                .catch {
                    _nowPlayingMovies.value = APIState.Error("No Internet Connection")
                }
                .collect { dataState ->
                    _nowPlayingMovies.value = transformListState(dataState)
                }
        }
    }

    fun getMovieDetail(movieId: String) {
        viewModelScope.launch {
            detailUseCase.execute(movieId)
//                .catch {
//                    _detailMovies.value = APIState.Error("No Internet Connection")
//                }
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
