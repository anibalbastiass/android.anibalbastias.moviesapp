package com.anibalbastias.moviesapp.feature.ui.navigation


sealed class Routes(val route: String) {

    object MoviesList : Routes("movies")

    object MoviesSearch : Routes("search")

    object Favorites : Routes("favorites")

    class MoviesDetail(
        movieId: Int = 0,
        val path: String = "movies/{$MOVIE_ID_KEY}",
    ) : Routes("movies/$movieId")

    class MoviesByMovieDetail(
        movieId: Int = 0,
        val path: String = "movies_by_movies/{$MOVIE_ID_KEY}",
    ) : Routes("movies_by_movies/$movieId")

    class MoviesDetailCast(
        movieId: Int = 0,
        creditId: String = "0",
        val path: String = "movies/{$MOVIE_ID_KEY}/cast/{$MOVIE_CREDIT_ID_KEY}",
    ) : Routes("movies/$movieId/cast/$creditId")
}