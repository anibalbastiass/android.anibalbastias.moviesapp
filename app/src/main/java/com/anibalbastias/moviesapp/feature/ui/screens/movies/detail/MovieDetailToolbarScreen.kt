package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.uikitcompose.utils.SharedUtils
import com.skydoves.landscapist.glide.GlideImage
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

    GlideImage(
        imageModel = movie.backdropPath,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .parallax(0.5f)
            .height(280.dp)
            .graphicsLayer {
                // change alpha of Image as the toolbar expands
                alpha = state.toolbarState.progress
            }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp),
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
    GlideImage(
        imageModel = movie.posterPath.replace("/w300/", "/w154/"),
        modifier = Modifier
            .width(120.dp)
            .height(180.dp)
    )
}
