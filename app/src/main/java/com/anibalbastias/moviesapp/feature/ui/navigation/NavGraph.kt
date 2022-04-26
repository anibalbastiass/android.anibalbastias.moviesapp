package com.anibalbastias.moviesapp.feature.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.ui.screens.favorites.FavoritesScreen
import com.anibalbastias.moviesapp.feature.ui.screens.movies.MoviesScreen

const val MOVIE_ID_KEY = "movieId"

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
@ExperimentalPagingApi
fun NavGraph() {
    val navController = rememberNavController()
    val moviesNavController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val moviesNavBackStackEntry by moviesNavController.currentBackStackEntryAsState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(moviesNavController, moviesNavBackStackEntry, navBackStackEntry) },
        bottomBar = { BottomNavGraph(navController) }
    ) {
        TabNavHost(
            navController = navController,
            moviesNavController = moviesNavController
        )
    }
}

@Composable
fun TopBar(
    navController: NavHostController,
    moviesNavBackStackEntry: NavBackStackEntry?,
    navBackStackEntry: NavBackStackEntry?,
) {
    val showBackButton = moviesNavBackStackEntry?.destination?.route == "movies/{movieId}" &&
            navBackStackEntry?.destination?.route == "movies"

    TopAppBar(
        backgroundColor = colorResource(id = R.color.backgroundColor),
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(if (showBackButton) 0.83f else 1f)
            ) {
                Image(
                    painterResource(R.drawable.movies_logo),
                    contentDescription = "MovieBox",
                    modifier = Modifier.height(20.dp)
                )
            }
        },
        navigationIcon = if (showBackButton) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = colorResource(id = R.color.white),
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        }
    )
}

@ExperimentalPagingApi
@ExperimentalFoundationApi
@Composable
fun TabNavHost(
    navController: NavHostController,
    moviesNavController: NavHostController,
) {
    NavHost(navController, startDestination = NavTabItem.Movies.route) {
        composable(NavTabItem.Movies.route) {
            MoviesScreen(moviesNavController)
        }
        composable(NavTabItem.Favorites.route) {
            FavoritesScreen()
        }
    }
}