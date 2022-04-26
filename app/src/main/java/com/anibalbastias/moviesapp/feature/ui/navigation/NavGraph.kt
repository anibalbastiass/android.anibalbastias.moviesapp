package com.anibalbastias.moviesapp.feature.ui.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar() },
        bottomBar = { BottomNavGraph(navController) }
    ) {
        TabNavHost(navController)
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        backgroundColor = colorResource(id = R.color.backgroundColor),
        title = {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painterResource(R.drawable.movies_logo),
                    contentDescription = "MovieBox",
                    modifier = Modifier.height(20.dp)
                )
            }
        }
    )
}

@ExperimentalPagingApi
@ExperimentalFoundationApi
@Composable
fun TabNavHost(
    navController: NavHostController
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