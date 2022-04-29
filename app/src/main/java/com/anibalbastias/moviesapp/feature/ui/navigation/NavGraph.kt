package com.anibalbastias.moviesapp.feature.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.moviesapp.feature.ui.screens.favorites.FavoritesScreen
import com.anibalbastias.moviesapp.feature.ui.screens.movies.MoviesScreen

const val MOVIE_ID_KEY = "movieId"

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagingApi
@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { BottomNavGraph(navController) }
    ) {
        TabNavHost(navController)
    }
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalPagingApi
@ExperimentalFoundationApi
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