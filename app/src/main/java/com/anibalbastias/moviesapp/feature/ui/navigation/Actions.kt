package com.anibalbastias.moviesapp.feature.ui.navigation

import androidx.navigation.NavHostController
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieCastItem
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

    val movieDetailByMovieAction: (movie: UiMovieItem) -> Unit = { movieId ->
        navHostController.navigate(
            route = Routes.MoviesByMovieDetail(movieId.id.toInt()).route
        )
    }

    val movieSearchAction: () -> Unit = {
        navHostController.navigate(Routes.MoviesSearch.route)
    }

    val movieCastAction: (movie: UiMovieItem, cast: UiMovieCastItem) -> Unit = { movie, cast ->
        navHostController.navigate(Routes.MoviesDetailCast(movie.id.toInt(), cast.creditId).route)
    }
}