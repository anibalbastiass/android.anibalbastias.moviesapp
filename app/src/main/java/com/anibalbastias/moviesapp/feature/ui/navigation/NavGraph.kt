package com.anibalbastias.moviesapp.feature.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.ui.screens.favorites.FavoritesScreen
import com.anibalbastias.moviesapp.feature.ui.screens.movies.MoviesScreen
import com.anibalbastias.moviesapp.feature.ui.screens.search.SearchScreen
import com.anibalbastias.uikitcompose.components.molecules.CenterTopAppBar

const val MOVIE_ID_KEY = "movieId"

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
@ExperimentalPagingApi
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