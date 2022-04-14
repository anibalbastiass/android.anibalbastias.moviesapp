package com.backbase.assignment.feature.ui.screens.list

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable

@Composable
fun MovieRatingView(voteAverage: Double) {
    CircularProgressIndicator(progress = voteAverage.toFloat())
}