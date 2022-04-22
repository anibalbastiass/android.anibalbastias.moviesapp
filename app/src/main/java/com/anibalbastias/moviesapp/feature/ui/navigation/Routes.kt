package com.anibalbastias.moviesapp.feature.ui.navigation


sealed class Routes(val route: String) {
    object MoviesList : Routes("movies")
    class MoviesDetail(
        userId: Int = 0,
        val path: String = "movies/{$MOVIE_ID_KEY}",
    ) : Routes("movies/$userId")
}