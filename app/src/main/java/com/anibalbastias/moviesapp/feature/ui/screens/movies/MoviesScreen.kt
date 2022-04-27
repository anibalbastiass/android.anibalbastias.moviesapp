package com.anibalbastias.moviesapp.feature.ui.screens.movies

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.FavoriteViewModel
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesPagingViewModel
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.moviesapp.feature.ui.navigation.MOVIE_ID_KEY
import com.anibalbastias.moviesapp.feature.ui.navigation.Routes
import com.anibalbastias.moviesapp.feature.ui.screens.movies.detail.MovieDetailScreen
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.MovieListScreen
import com.anibalbastias.uikitcompose.utils.SharedUtils

@ExperimentalFoundationApi
@ExperimentalPagingApi
@Composable
fun MoviesScreen(
    moviesViewModel: MoviesViewModel = hiltViewModel(),
    moviesPagingViewModel: MoviesPagingViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
) {
    val moviesNavController = rememberNavController()
    val movieActions = remember(moviesNavController) { Actions(moviesNavController) }

    MoviesNavHost(
        moviesNavController = moviesNavController,
        moviesViewModel = moviesViewModel,
        moviesPagingViewModel = moviesPagingViewModel,
        favoriteViewModel = favoriteViewModel,
        movieActions = movieActions
    )
}

@ExperimentalFoundationApi
@ExperimentalPagingApi
@Composable
fun MoviesNavHost(
    moviesNavController: NavHostController,
    movieActions: Actions,
    moviesViewModel: MoviesViewModel,
    moviesPagingViewModel: MoviesPagingViewModel,
    favoriteViewModel: FavoriteViewModel,
) {
    SharedUtils.SharedListRootContainer(movieActions.goBackAction) { tweenSpec, selectedItem ->
        NavHost(
            navController = moviesNavController,
            startDestination = Routes.MoviesList.route
        ) {
            // Movie List
            composable(route = Routes.MoviesList.route) {
                Crossfade(
                    targetState = selectedItem,
                    animationSpec = tweenSpec
                ) { item ->
                    Log.d("Index", item.toString())

                    MovieListScreen(
                        moviesViewModel = moviesViewModel,
                        moviesPagingViewModel = moviesPagingViewModel,
                        favoriteViewModel = favoriteViewModel,
                        movieDetailAction = movieActions.movieDetailAction
                    )
                }
            }

            // Movie Detail
            composable(
                route = Routes.MoviesDetail().path,
                arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType })
            ) { backStackEntry ->
                Crossfade(
                    targetState = selectedItem,
                    animationSpec = tweenSpec
                ) { item ->
                    MovieDetailScreen(
                        movieId = backStackEntry.arguments?.getInt(MOVIE_ID_KEY),
                        moviesViewModel = moviesViewModel,
                        index = item
                    )
                }
            }
        }
    }
}
