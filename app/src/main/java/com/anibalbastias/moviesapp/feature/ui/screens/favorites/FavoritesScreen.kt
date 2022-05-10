package com.anibalbastias.moviesapp.feature.ui.screens.favorites

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
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
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.moviesapp.feature.ui.navigation.MOVIE_ID_KEY
import com.anibalbastias.moviesapp.feature.ui.navigation.Routes
import com.anibalbastias.moviesapp.feature.ui.screens.movies.detail.MovieDetailScreen
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeViewModel
import com.anibalbastias.uikitcompose.utils.SharedUtils

@ExperimentalMaterialApi
@ExperimentalPagingApi
@ExperimentalFoundationApi
@Composable
fun FavoritesScreen(
    moviesViewModel: MoviesViewModel = hiltViewModel(),
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    youTubeViewModel: YouTubeViewModel = hiltViewModel(),
) {
    val favoritesNavController = rememberNavController()
    val movieActions = remember(favoritesNavController) { Actions(favoritesNavController) }

    FavoritesNavHost(
        favoritesNavController = favoritesNavController,
        favoriteViewModel = favoriteViewModel,
        moviesViewModel = moviesViewModel,
        youTubeViewModel = youTubeViewModel,
        movieActions = movieActions
    )
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagingApi
@Composable
fun FavoritesNavHost(
    movieActions: Actions,
    favoritesNavController: NavHostController,
    favoriteViewModel: FavoriteViewModel,
    moviesViewModel: MoviesViewModel,
    youTubeViewModel: YouTubeViewModel,
) {
    SharedUtils.SharedListRootContainer(movieActions.goBackAction) { tweenSpec, selectedItem ->
        NavHost(
            navController = favoritesNavController,
            startDestination = Routes.Favorites.route
        ) {
            // Movie List
            composable(route = Routes.Favorites.route) {
                Crossfade(
                    targetState = selectedItem,
                    animationSpec = tweenSpec
                ) { item ->
                    Log.d("Index", item.toString())

                    FavoritesListScreen(
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
                        moviesViewModel = moviesViewModel,
                        youTubeViewModel = youTubeViewModel,
                        movieId = backStackEntry.arguments?.getInt(MOVIE_ID_KEY),
                        index = item,
                        movieActions = movieActions
                    )
                }
            }

            // Movie Detail by Movie
            composable(
                route = Routes.MoviesByMovieDetail().path,
                arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType })
            ) { backStackEntry ->
                MovieDetailScreen(
                    movieId = backStackEntry.arguments?.getInt(MOVIE_ID_KEY),
                    moviesViewModel = moviesViewModel,
                    youTubeViewModel = youTubeViewModel,
                    movieActions = movieActions
                )
            }
        }
    }
}