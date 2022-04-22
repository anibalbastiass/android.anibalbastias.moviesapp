package com.anibalbastias.moviesapp.feature.ui.navigation

import androidx.navigation.NavHostController

class Actions(navHostController: NavHostController) {

    val goBackAction: () -> Unit = {
        navHostController.popBackStack()
    }

    val movieListAction: () -> Unit = {
        navHostController.navigate(Routes.MoviesList.route)
    }

    val movieDetailAction: (movieId: Int) -> Unit = { movieId ->
        navHostController.navigate(
            route = Routes.MoviesDetail(movieId).route
        )
    }


}