package com.backbase.assignment.feature.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.backbase.assignment.feature.presentation.viewmodel.MoviesPagedViewModel
import com.backbase.assignment.feature.presentation.viewmodel.MoviesViewModel
import com.backbase.assignment.feature.ui.navigation.Actions.Companion.MOVIE_ID_KEY
import com.backbase.assignment.feature.ui.screens.list.MovieDetailScreen
import com.backbase.assignment.feature.ui.screens.list.MovieListScreen

@Composable
fun NavGraph(
    startDestination: String = Routes.MOVIES_LIST.route,
    moviesViewModel: MoviesViewModel,
    moviesPagedViewModel: MoviesPagedViewModel,
) {
    val navController = rememberNavController()
    val actions = remember(navController) { Actions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Movie List
        composable(route = Routes.MOVIES_LIST.route) {
            MovieListScreen(
                moviesViewModel = moviesViewModel,
                moviesPagedViewModel = moviesPagedViewModel,
                movieDetailAction = actions.movieDetailAction
            )
        }

        // Movie Detail
        composable(
            route = Routes.MOVIES_DETAIL.route,
            arguments = listOf(navArgument(MOVIE_ID_KEY) { type = NavType.IntType })
        ) { backStackEntry ->
            MovieDetailScreen(
                movieId = backStackEntry.arguments?.getInt(MOVIE_ID_KEY),
                moviesViewModel = moviesViewModel
            )
        }
    }
}