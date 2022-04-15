package com.backbase.assignment.feature.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import com.backbase.assignment.feature.presentation.viewmodel.MoviesPagingViewModel
import com.backbase.assignment.feature.presentation.viewmodel.MoviesViewModel
import com.backbase.assignment.feature.ui.screens.detail.MovieDetailScreen
import com.backbase.assignment.feature.ui.screens.list.MovieListScreen

const val MOVIE_ID_KEY = "movieId"

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
    val onBack = { actions.goBackAction() }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Movie List
        composable(route = Routes.MoviesList.route) {
            MovieListScreen(
                moviesViewModel = moviesViewModel,
                moviesPagingViewModel = moviesPagingViewModel,
                movieDetailAction = actions.movieDetailAction
            )
        }

        // Movie Detail
        composable(
            route = Routes.MoviesDetail().path,
            arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            MovieDetailScreen(
                movieId = backStackEntry.arguments?.getInt(MOVIE_ID_KEY),
                moviesViewModel = moviesViewModel,
                onBack = onBack
            )
        }
    }
}