package com.backbase.assignment.feature.ui.screens.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.backbase.assignment.R
import com.backbase.assignment.feature.presentation.viewmodel.MoviesPagingViewModel
import com.backbase.assignment.feature.presentation.viewmodel.MoviesViewModel
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
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.backgroundColor),
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Image(
                            painterResource(R.drawable.movies_logo),
                            contentDescription = "MovieBox",
                            modifier = Modifier.height(20.dp)
                        )
                    }
                }
            )
        },
        content = {
            val isRefreshing by moviesViewModel.isRefreshing.collectAsState()

            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = {
                    moviesViewModel.getNowPlayingMovies()
                }
            ) {
                PopularMoviesView(nowPlayingState, moviesListItems, movieDetailAction)
            }
        }
    )
}