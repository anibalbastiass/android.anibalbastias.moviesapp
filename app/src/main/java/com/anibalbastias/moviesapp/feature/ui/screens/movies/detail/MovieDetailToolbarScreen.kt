package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.uikitcompose.utils.SharedUtils
import me.onebone.toolbar.CollapsingToolbarScaffoldState
import me.onebone.toolbar.CollapsingToolbarScope

@Composable
fun CollapsingToolbarScope.MovieDetailToolBarScreen(
    movie: UiMovieDetail,
    state: CollapsingToolbarScaffoldState,
    index: Int,
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.backdropPath)
            .crossfade(true)
            .build(),
        contentDescription = movie.originalTitle,
        modifier = Modifier
            .parallax(0.5f)
            .height(280.dp)
            .graphicsLayer {
                // change alpha of Image as the toolbar expands
                alpha = state.toolbarState.progress
            },
        contentScale = ContentScale.Crop
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp)
    ) {
        SharedUtils.SharedDetailBoxContainer(movie.posterPath + index) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.originalTitle,
                modifier = Modifier
                    .height(200.dp)
            )
        }
    }
}