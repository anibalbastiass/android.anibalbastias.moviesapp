package com.anibalbastias.moviesapp.feature.ui.navigation

import androidx.navigation.NavHostController
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem

class Actions(navHostController: NavHostController) {

    val goBackAction: () -> Unit = {
        navHostController.popBackStack()
    }

    val movieDetailAction: (movie: UiMovieItem) -> Unit = { movieId ->
        navHostController.navigate(
            route = Routes.MoviesDetail(movieId.id.toInt()).route
        )
    }

    val movieSearchAction: () -> Unit = {
        navHostController.navigate(Routes.MoviesSearch.route)
    }
}