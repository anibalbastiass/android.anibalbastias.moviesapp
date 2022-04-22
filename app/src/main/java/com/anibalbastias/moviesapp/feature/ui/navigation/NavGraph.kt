package com.anibalbastias.moviesapp.feature.ui.navigation

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.uikitcompose.utils.SharedUtils.SharedListRootContainer
import com.anibalbastias.uikitcompose.utils.SharedUtils.changeItem
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesPagingViewModel
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.screens.detail.MovieDetailScreen
import com.anibalbastias.moviesapp.feature.ui.screens.list.MovieListScreen
import com.mxalbert.sharedelements.LocalSharedElementsRootScope

const val MOVIE_ID_KEY = "movieId"

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
@ExperimentalPagingApi
fun NavGraph(
    startDestination: String = Routes.MoviesList.route,
    moviesViewModel: MoviesViewModel = hiltViewModel(),
    moviesPagingViewModel: MoviesPagingViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    SharedListRootContainer(actions.goBackAction) { tweenSpec, selectedItem ->
        val scope = LocalSharedElementsRootScope.current!!
        val onBack = {
            scope.changeItem(-1)
            actions.goBackAction()
        }

        NavHost(
            navController = navController,
            startDestination = startDestination
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
                        movieDetailAction = actions.movieDetailAction
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
                        onBack = onBack,
                        index = item
                    )
                }
            }
        }
    }
}