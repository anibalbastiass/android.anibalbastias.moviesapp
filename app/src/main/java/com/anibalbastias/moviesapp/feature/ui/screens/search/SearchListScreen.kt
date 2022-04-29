package com.anibalbastias.moviesapp.feature.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.StickyHeaderMovie

@Composable
fun SearchListScreen() {
    Column(
//        modifier = Modifier.fillMaxSize()
    ) {

    }
    StickyHeaderMovie(title = stringResource(id = R.string.results))
}