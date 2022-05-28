package com.anibalbastias.moviesapp.feature.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anibalbastias.moviesapp.feature.domain.usecase.local.AddFavoriteMovieUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.local.GetFavoriteMoviesUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.local.RemoveFavoriteMovieUseCase
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: GetFavoriteMoviesUseCase,
    private val addFavoriteUseCase: AddFavoriteMovieUseCase,
    private val removeFavoriteMovieUseCase: RemoveFavoriteMovieUseCase,
) : ViewModel() {

    private val _favoriteMovies = MutableStateFlow<List<UiMovieItem>>(listOf())
    val favoriteMovies: StateFlow<List<UiMovieItem>>
        get() = _favoriteMovies

    private val _updateMovie = MutableStateFlow(false)

    fun getFavoriteList() {
        viewModelScope.launch {
            favoriteUseCase.execute().collect { list ->
                _favoriteMovies.value = list
            }
        }
    }

    fun addFavoriteMovie(movie: UiMovieItem) {
        viewModelScope.launch {
            addFavoriteUseCase.execute(movie)
                .collect {
                    _updateMovie.value = it
                }
        }
    }

    fun removeFavoriteMovie(movie: UiMovieItem) {
        viewModelScope.launch {
            removeFavoriteMovieUseCase.execute(movie)
                .collect {
                    _updateMovie.value = it
                }
        }
    }
}