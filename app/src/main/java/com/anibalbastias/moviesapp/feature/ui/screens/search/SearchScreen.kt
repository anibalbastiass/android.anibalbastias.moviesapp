package com.anibalbastias.moviesapp.feature.ui.screens.search

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.SearchViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.moviesapp.feature.ui.screens.favorites.EmptyMoviesScreen
import com.anibalbastias.uikitcompose.components.molecules.SearchTopBar
import com.anibalbastias.uikitcompose.utils.SharedUtils

@OptIn(ExperimentalPagingApi::class)
@Composable
fun SearchScreen(
    movieActions: Actions,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {
    val searchText = searchViewModel.searchText.collectAsState().value
    val moviesListItems = searchViewModel.searchMovies(searchText).collectAsLazyPagingItems()

    SharedUtils.SharedListRootContainer(movieActions.goBackAction) { tweenSpec, selectedItem ->
        Crossfade(
            targetState = selectedItem,
            animationSpec = tweenSpec
        ) { item ->
            Log.d("Index", item.toString())

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
                    onSearchTextChanged = {
                        searchViewModel.updateSearchText(it)
                    },
                    matchesFound = moviesListItems.itemCount > 0,
                    onNavigateBack = { movieActions.goBackAction() },
                    onClearClick = {
                        searchViewModel.onClearClick()
                    },
                    results = {
                        SearchListScreen(moviesListItems) { movie ->
                            searchViewModel.addSearchMovie(movie.originalTitle)
                            movieActions.movieDetailAction(movie)
                        }
                    },
                    noResults = {
                        EmptyMoviesScreen(stringResource(id = R.string.movies_empty))
                    },
                    default = {
                        SavedSearches(
                            searchViewModel = searchViewModel,
                            onSelectSaved = { query ->
                                searchViewModel.updateSearchText(query)
                            },
                            onRemoveSaved = { title ->
                                searchViewModel.removeSavedSearch(title)
                                searchViewModel.getSavedSearchesMovies()
                            },
                            onRemoveAll = {
                                searchViewModel.clearAllSavedMovies()
                                searchViewModel.getSavedSearchesMovies()
                            }
                        )
                    }
                )
            }
        }
    }
}
