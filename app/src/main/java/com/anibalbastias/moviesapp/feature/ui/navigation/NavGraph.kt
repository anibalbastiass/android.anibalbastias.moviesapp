package com.anibalbastias.moviesapp.feature.ui.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anibalbastias.moviesapp.feature.ui.screens.favorites.FavoritesScreen
import com.anibalbastias.moviesapp.feature.ui.screens.movies.MoviesScreen
import com.anibalbastias.uikitcompose.utils.isLandscapeOrientation

const val MOVIE_ID_KEY = "movieId"
const val MOVIE_CREDIT_ID_KEY = "movieCreditId"

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val isExpandedScreen = isLandscapeOrientation()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            if (!isExpandedScreen) {
                BottomNavGraph(navController)
            }
        }
    ) {
        TabNavHost(navController)
    }
}

@Composable
fun TabNavHost(
    navController: NavHostController,
) {
    NavHost(navController, startDestination = NavTabItem.Movies.route) {
        composable(NavTabItem.Movies.route) {
            MoviesScreen()
        }
        composable(NavTabItem.Favorites.route) {
            FavoritesScreen()
        }
    }
}