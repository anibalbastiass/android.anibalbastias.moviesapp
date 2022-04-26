package com.anibalbastias.moviesapp.feature.ui.screens.favorites

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
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesPagingViewModel
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.moviesapp.feature.ui.navigation.MOVIE_ID_KEY
import com.anibalbastias.moviesapp.feature.ui.navigation.Routes
import com.anibalbastias.moviesapp.feature.ui.screens.movies.detail.MovieDetailScreen
import com.anibalbastias.uikitcompose.utils.SharedUtils

@ExperimentalPagingApi
@ExperimentalFoundationApi
@Composable
fun FavoritesScreen(
    moviesViewModel: MoviesViewModel = hiltViewModel(),
    moviesPagingViewModel: MoviesPagingViewModel = hiltViewModel(),
) {
    val favoritesNavController = rememberNavController()
    val movieActions = remember(favoritesNavController) { Actions(favoritesNavController) }

    SharedUtils.SharedListRootContainer(movieActions.goBackAction) { tweenSpec, selectedItem ->
        FavoritesNavHost(
            favoritesNavController = favoritesNavController,
            selectedItem = selectedItem,
            tweenSpec = tweenSpec,
            moviesPagingViewModel = moviesPagingViewModel,
            moviesViewModel = moviesViewModel,
            movieActions = movieActions
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalPagingApi
@Composable
fun FavoritesNavHost(
    favoritesNavController: NavHostController,
    selectedItem: Int,
    tweenSpec: FiniteAnimationSpec<Float>,
    moviesPagingViewModel: MoviesPagingViewModel,
    movieActions: Actions,
    moviesViewModel: MoviesViewModel,
) {
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
                    moviesPagingViewModel = moviesPagingViewModel,
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