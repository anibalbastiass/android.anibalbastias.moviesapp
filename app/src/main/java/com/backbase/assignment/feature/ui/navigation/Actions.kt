package com.backbase.assignment.feature.ui.navigation

import androidx.navigation.NavHostController

class Actions(navHostController: NavHostController) {

    companion object {
        const val MOVIE_ID_KEY = "movieId"
    }

    val movieListAction: () -> Unit = {
        navHostController.navigate(Routes.MOVIES_LIST.route)
    }

    val movieDetailAction: (movieId: Int) -> Unit = { movieId ->
        navHostController.navigate(
            route = Routes.MOVIES_DETAIL.route.replace("{$MOVIE_ID_KEY}", "$movieId")
        )
    }
}