package com.anibalbastias.moviesapp.feature.ui.screens.movies.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesPagingViewModel
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
@ExperimentalFoundationApi
@ExperimentalPagingApi
fun MovieListScreen(
    moviesViewModel: MoviesViewModel,
    moviesPagingViewModel: MoviesPagingViewModel,
    movieDetailAction: (movieId: Int) -> Unit,
) {
    val nowPlayingState = moviesViewModel.nowPlayingMovies.collectAsState().value
    moviesViewModel.getNowPlayingMovies()

    val moviesListItems = moviesPagingViewModel.pagedMovieList.collectAsLazyPagingItems()

    Scaffold(
        content = {
            val isRefreshing by moviesViewModel.isRefreshing.collectAsState()

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = {
                    moviesViewModel.getNowPlayingMovies(true)
                }
            ) {
                PopularMoviesView(
                    nowPlayingState,
                    moviesListItems,
                    movieDetailAction) { movie ->
                    moviesViewModel.updateMovie(movie)
                }
            }
        }
    )
}