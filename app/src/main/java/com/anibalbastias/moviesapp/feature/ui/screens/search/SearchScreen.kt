package com.anibalbastias.moviesapp.feature.ui.screens.search

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.SearchViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.moviesapp.feature.ui.screens.favorites.EmptyMoviesScreen
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.StickyHeaderMovie
import com.anibalbastias.uikitcompose.components.atom.HorizontalDivider
import com.anibalbastias.uikitcompose.components.molecules.SearchTopBar
import com.anibalbastias.uikitcompose.utils.SharedUtils
import com.anibalbastias.uikitcompose.utils.rememberForeverLazyListState

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagingApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
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
                            onRemoveSaved = { searchId ->
                                searchViewModel.removeSavedSearch(searchId)
                                searchViewModel.getSavedSearchesMovies()
                            }
                        )
                    }
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagingApi
@ExperimentalFoundationApi
@Composable
fun SavedSearches(
    searchViewModel: SearchViewModel,
    onSelectSaved: (query: String) -> Unit,
    onRemoveSaved: (id: String) -> Unit,
) {
    searchViewModel.getSavedSearchesMovies()
    val savedSearches = searchViewModel.savedSearches

    val lazyListState = rememberForeverLazyListState(key = "SavedMovies")
    LazyColumn(state = lazyListState) {
        stickyHeader {
            StickyHeaderMovie(title = stringResource(id = R.string.saved_movies))
        }

        items(savedSearches) { item ->
            Column(modifier = Modifier
                .clickable {
                    onSelectSaved(item.title)
                }
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_time),
                        tint = colorResource(id = R.color.textColor),
                        modifier = Modifier
                            .padding(top = 18.dp, bottom = 18.dp, start = 18.dp, end = 0.dp)
                            .size(30.dp)
                            .wrapContentSize(Alignment.Center)
                            .fillParentMaxWidth(0.1f),
                        contentDescription = "Time"
                    )
                    Text(
                        text = item.title,
                        color = colorResource(id = R.color.textColor),
                        style = MaterialTheme.typography.body1,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(20.dp)
                            .wrapContentSize(Alignment.Center)
                            .fillParentMaxWidth(0.63f)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        tint = colorResource(id = R.color.textColor),
                        modifier = Modifier
                            .size(60.dp)
                            .wrapContentSize(Alignment.Center)
                            .clickable { onRemoveSaved(item.id) }
                            .fillParentMaxWidth(0.1f)
                            .padding(top = 18.dp, bottom = 10.dp, start = 0.dp, end = 10.dp),
                        contentDescription = "Close"
                    )
                }
                HorizontalDivider()
            }
        }
    }
}
