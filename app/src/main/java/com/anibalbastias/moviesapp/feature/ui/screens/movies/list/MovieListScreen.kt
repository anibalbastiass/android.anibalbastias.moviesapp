package com.anibalbastias.moviesapp.feature.ui.screens.movies.list

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.paging.compose.collectAsLazyPagingItems
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.FavoriteViewModel
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesPagingViewModel
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.moviesapp.feature.ui.navigation.AppTopBar
import com.anibalbastias.moviesapp.feature.ui.navigation.TopBarType
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun MovieListScreen(
    moviesViewModel: MoviesViewModel,
    moviesPagingViewModel: MoviesPagingViewModel,
    movieActions: Actions,
    favoriteViewModel: FavoriteViewModel,
) {
    val nowPlayingState = moviesViewModel.nowPlayingMovies.collectAsState().value
    moviesViewModel.getNowPlayingMovies()

    val moviesListItems = moviesPagingViewModel.pagedMovieList.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AppTopBar(
                type = TopBarType.MOVIE_LIST,
                onSearchBarClick = { movieActions.movieSearchAction() }
            )
        },
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
                    movieActions.movieDetailAction
                ) { movie, isFavorite ->

                    if (isFavorite) {
                        favoriteViewModel.addFavoriteMovie(movie)
                    } else {
                        favoriteViewModel.removeFavoriteMovie(movie)
                    }
                }
            }
        }
    )
}