package com.anibalbastias.moviesapp.feature.ui.navigation

import com.anibalbastias.moviesapp.R

sealed class NavTabItem(var route: String, var icon: Int, var title: String) {
    object Movies : NavTabItem("movies", R.drawable.ic_movies, "Movies")
    object Favorites : NavTabItem("favorites", R.drawable.ic_favorites, "Favorites")
}