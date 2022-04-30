package com.anibalbastias.moviesapp.feature.ui.screens.search

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.SearchViewModel
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.components.atom.Body2
import com.anibalbastias.uikitcompose.components.atom.HorizontalDivider
import com.anibalbastias.uikitcompose.utils.rememberForeverLazyListState

@ExperimentalMaterialApi
@ExperimentalPagingApi
@ExperimentalFoundationApi
@Composable
fun SavedSearches(
    searchViewModel: SearchViewModel,
    onSelectSaved: (query: String) -> Unit,
    onRemoveSaved: (id: String) -> Unit,
    onRemoveAll: () -> Unit,
) {
    searchViewModel.getSavedSearchesMovies()
    val savedSearches = searchViewModel.savedSearches

    val lazyListState = rememberForeverLazyListState(key = "SavedMovies")
    LazyColumn(state = lazyListState) {
        stickyHeader {
            StickyHeaderSavedMovie(
                title = stringResource(id = R.string.saved_movies),
                onRemoveAll = onRemoveAll
            )
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
                            .clickable { onRemoveSaved(item.title) }
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

@Composable
fun StickyHeaderSavedMovie(
    title: String,
    onRemoveAll: () -> Unit,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = colorResource(id = R.color.backgroundSecondaryColor))
        .padding(16.dp)
    ) {
        Body1(
            text = title,
            color = colorResource(id = R.color.titleColor),
            modifier = Modifier.fillMaxWidth(0.83f)
        )
        Body2(
            text = stringResource(id = R.string.clear_all),
            color = colorResource(id = R.color.textColor),
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .clickable { onRemoveAll() }
        )
    }
}