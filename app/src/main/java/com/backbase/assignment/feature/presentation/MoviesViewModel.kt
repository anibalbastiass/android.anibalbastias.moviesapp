package com.backbase.assignment.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.GetNowPlayingMoviesUseCase
import com.backbase.assignment.feature.domain.UiMovieDataState
import com.backbase.assignment.feature.domain.model.DomainMovieItem
import com.backbase.assignment.feature.presentation.mapper.UiMovieItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val useCase: GetNowPlayingMoviesUseCase,
    private val mapper: UiMovieItemMapper
) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<UiMovieDataState>()
    val nowPlayingMovies: LiveData<UiMovieDataState>
        get() = _nowPlayingMovies

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            useCase.execute()
                .catch {
                    _nowPlayingMovies.value = APIState.Error("No Internet Connection")
                }
                .collect { dataState ->
                    _nowPlayingMovies.value = transformState(dataState)
                }
        }
    }

    private fun transformState(dataState: APIState<List<DomainMovieItem>>) = when (dataState) {
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
