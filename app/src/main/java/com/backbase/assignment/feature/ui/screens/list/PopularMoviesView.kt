package com.backbase.assignment.feature.ui.screens.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.components.atom.Body2
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.anibalbastias.uikitcompose.utils.rememberForeverLazyListState
import com.backbase.assignment.R
import com.backbase.assignment.feature.domain.UiMovieDataState
import com.backbase.assignment.feature.presentation.model.UiMovieItem

@ExperimentalFoundationApi
@Composable
fun PopularMoviesView(
    nowPlayingState: UiMovieDataState,
    moviesListItems: LazyPagingItems<UiMovieItem>,
    movieDetailAction: (movieId: Int) -> Unit,
) {
    val lazyListState = rememberForeverLazyListState(key = "PopularMovies")

    when (moviesListItems.itemCount) {
        0 -> LoadingItem()
        else -> {
            LazyColumn(state = lazyListState) {
                stickyHeader {
                    StickyHeaderMovie(title = stringResource(id = R.string.now_playing))
                }

                item {
                    NowPlayingMoviesView(nowPlayingState, movieDetailAction)
                }

                stickyHeader {
                    StickyHeaderMovie(title = stringResource(id = R.string.most_popular))
                }

                items(moviesListItems) { item ->
                    if (item != null) {
                        MovieListItemView(movie = item, movieDetailAction = movieDetailAction)
                    }
                }
                loadState(moviesListItems.loadState)
            }
        }
    }
}

@Composable
fun StickyHeaderMovie(title: String) {
    Body1(
        text = title,
        color = colorResource(id = R.color.titleColor),
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.backgroundSecondaryColor))
            .padding(16.dp)
    )
}

fun LazyListScope.loadState(loadState: CombinedLoadStates) {
    loadState.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                item { LoadingItem() }
            }
            loadState.append is LoadState.Loading -> {
                item { LoadingItem() }
            }
            loadState.refresh is LoadState.Error -> {}
            loadState.append is LoadState.Error -> {}
        }
    }
}

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier =
        Modifier
            .background(colorResource(id = R.color.backgroundSecondaryColor))
            .fillMaxSize()
            .padding(16.dp)
            .wrapContentHeight(Alignment.CenterVertically)
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun MovieListItemView(
    movie: UiMovieItem,
    movieDetailAction: (movieId: Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable { movieDetailAction.invoke(movie.id.toInt()) }
    ) {
        Row(
            modifier = Modifier
                .background(color = colorResource(id = R.color.backgroundColor))
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterPath)
                    .crossfade(true)
                    .build(),
                modifier = Modifier
                    .width(60.dp)
                    .height(100.dp),
                contentDescription = movie.originalTitle
            )

            Column(modifier = Modifier
                .padding(10.dp)
                .weight(1f)
            ) {
                Body1(text = movie.originalTitle, color = colorResource(id = R.color.textColor))
                Body2(text = movie.releaseDate, color = colorResource(id = R.color.textColor))
            }

            MovieRatingView(movie.voteAverage)
        }

        Box(modifier = Modifier
            .background(color = colorResource(id = R.color.backgroundSecondaryColor))
            .height(10.dp)
            .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun StickyHeaderMoviePreview() {
    Surface {
        UIKitComposeTheme {
            StickyHeaderMovie("Popular Movies")
        }
    }
}

@Preview
@Composable
fun MovieListItemViewPreview() {
    Surface {
        UIKitComposeTheme {
            val movie = UiMovieItem(
                id = 1L,
                posterPath = "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg",
                originalTitle = "The Mask",
                voteAverage = 8.5,
                releaseDate = "March 30, 2022"
            )

            MovieListItemView(movie) {}
        }
    }
}