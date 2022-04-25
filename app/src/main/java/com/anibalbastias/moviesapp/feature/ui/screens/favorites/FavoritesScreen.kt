package com.anibalbastias.moviesapp.feature.ui.screens.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.StickyHeaderMovie
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.utils.rememberForeverLazyListState

@ExperimentalFoundationApi
@Composable
fun FavoritesScreen(
    moviesViewModel: MoviesViewModel = hiltViewModel(),
) {
    EmptyFavoriteScreen()

//    val lazyListState = rememberForeverLazyListState(key = "Favorites")
//
//    LazyColumn(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(id = R.color.backgroundColor)),
//        state = lazyListState
//    ) {
//        stickyHeader {
//            StickyHeaderMovie(title = stringResource(id = R.string.favorites))
//        }
//
//        item {
//
//        }
//    }
}

@Composable
fun EmptyFavoriteScreen() {
    ConstraintLayout(modifier = Modifier
        .padding(bottom = 50.dp)
        .fillMaxSize()
        .background(colorResource(id = R.color.backgroundColor))
    ) {
        val (circle, loader, text) = createRefs()

        Box(
            modifier = Modifier
                .constrainAs(circle) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .clip(CircleShape)
                .background(colorResource(id = R.color.white))
                .size(120.dp)
        )

        Box(
            modifier = Modifier
                .size(100.dp)
                .constrainAs(loader) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(start = 20.dp)
        ) {
            Loader()
        }

        Body1(
            text = stringResource(id = R.string.favorites_empty),
            color = colorResource(id = R.color.textColor),
            modifier = Modifier
                .padding(top = 30.dp)
                .constrainAs(text) {
                    top.linkTo(loader.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.movie))
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
}