package com.anibalbastias.moviesapp.feature.ui.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.LoadingItem
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.MovieListItemView
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.StickyHeaderMovie
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.loadState
import com.anibalbastias.uikitcompose.components.molecules.FavoriteSwipeCard
import com.anibalbastias.uikitcompose.utils.rememberForeverLazyListState
import com.mxalbert.sharedelements.LocalSharedElementsRootScope

@ExperimentalFoundationApi
@Composable
fun SearchListScreen(
    moviesListItems: LazyPagingItems<UiMovieItem>,
    movieDetailAction: (movie: UiMovieItem) -> Unit
) {
    val lazyListState = rememberForeverLazyListState(key = "SearchMovies")
    val scope = LocalSharedElementsRootScope.current!!

    when (moviesListItems.itemCount) {
        0 -> LoadingItem()
        else -> {
            LazyColumn(state = lazyListState) {
                stickyHeader {
                    StickyHeaderMovie(title = stringResource(id = R.string.results))
                }

                itemsIndexed(moviesListItems) { index, item ->
                    if (item != null) {
                        MovieListItemView(
                            index = index,
                            movie = item,
                            movieDetailAction = movieDetailAction,
                            scope = scope
                        )
                    }
                }
                loadState(moviesListItems.loadState)
            }
        }
    }
}