package com.anibalbastias.moviesapp.feature.ui.screens.search

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.SearchViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.uikitcompose.components.molecules.SearchTopBar

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    movieActions: Actions,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val searchText = searchViewModel.searchText.collectAsState().value

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.backgroundColor))
            .fillMaxSize()
    ) {
        SearchTopBar(
            backgroundColor = colorResource(id = R.color.backgroundColor),
            contentColor = colorResource(id = R.color.textColor),
            searchText = searchText,
            placeholderText = "Search movies",
            onSearchTextChanged = { searchViewModel.onSearchTextChanged(it) },
            onClearClick = { searchViewModel.onClearClick() },
            onNavigateBack = { movieActions.goBackAction() },
            matchesFound = true,
            results = {
                SearchListScreen()
            },
            noResults = {

            }
        )
    }
}