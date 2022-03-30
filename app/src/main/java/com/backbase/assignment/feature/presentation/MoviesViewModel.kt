package com.backbase.assignment.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backbase.assignment.feature.data.MovieDataState
import com.backbase.assignment.feature.data.state.APIState
import com.backbase.assignment.feature.domain.GetNowPlayingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val useCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    private val _nowPlayingMovies = MutableLiveData<MovieDataState>()
    val nowPlayingMovies: LiveData<MovieDataState>
        get() = _nowPlayingMovies

    fun getNowPlayingMovies() {
        viewModelScope.launch {
            useCase.execute()
                .catch {
                    _nowPlayingMovies.value = APIState.Error("No Internet Connection")
                }
                .collect { dataState ->
                    _nowPlayingMovies.value = dataState
                }
        }
    }
}
