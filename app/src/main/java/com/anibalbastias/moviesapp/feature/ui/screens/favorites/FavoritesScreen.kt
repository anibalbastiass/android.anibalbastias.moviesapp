package com.anibalbastias.moviesapp.feature.ui.screens.favorites

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel

@Composable
fun FavoritesScreen(
    moviesViewModel: MoviesViewModel = hiltViewModel(),
) {
    Text(text = "Hello World")
}