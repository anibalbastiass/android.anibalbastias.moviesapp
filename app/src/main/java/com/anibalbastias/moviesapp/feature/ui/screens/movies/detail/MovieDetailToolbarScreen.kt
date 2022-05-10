package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
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

    Image(
        painter = rememberAsyncImagePainter(movie.backdropPath),
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
        modifier = Modifier.fillMaxSize().padding(top = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        if (index == -1) {
            PosterPathImage(movie)
        } else {
            SharedUtils.SharedDetailBoxContainer(movie.posterPath + index) {
                PosterPathImage(movie)
            }
        }
    }
}

@Composable
fun PosterPathImage(movie: UiMovieDetail) {
    Image(
        painter = rememberAsyncImagePainter(movie.posterPath.replace("/w300/", "/w154/")),
        contentDescription = movie.originalTitle,
        modifier = Modifier
            .width(120.dp)
            .height(180.dp)
    )
}
