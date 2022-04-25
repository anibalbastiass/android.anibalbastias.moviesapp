package com.anibalbastias.moviesapp.feature.ui.screens.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.StickyHeaderMovie
import com.anibalbastias.uikitcompose.utils.rememberForeverLazyListState

@ExperimentalFoundationApi
@Composable
fun FavoritesScreen(
    moviesViewModel: MoviesViewModel = hiltViewModel(),
) {
    val lazyListState = rememberForeverLazyListState(key = "Favorites")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.backgroundColor)),
        state = lazyListState
    ) {
        stickyHeader {
            StickyHeaderMovie(title = stringResource(id = R.string.favorites))
        }
    }
}