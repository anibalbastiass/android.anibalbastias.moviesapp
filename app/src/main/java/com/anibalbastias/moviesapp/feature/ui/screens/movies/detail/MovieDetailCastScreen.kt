package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.runtime.Composable
import com.anibalbastias.uikitcompose.components.atom.Body1

@Composable
fun MovieDetailCastScreen(creditId: String) {
    Body1(text = "$creditId")
}