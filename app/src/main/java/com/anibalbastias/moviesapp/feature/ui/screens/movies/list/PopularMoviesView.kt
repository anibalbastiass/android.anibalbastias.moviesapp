@file:OptIn(ExperimentalMaterialApi::class)

package com.anibalbastias.moviesapp.feature.ui.screens.movies.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.domain.UiMovieDataState
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.components.atom.Body2
import com.anibalbastias.uikitcompose.components.molecules.FavoriteSwipeCard
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.anibalbastias.uikitcompose.utils.SharedUtils
import com.anibalbastias.uikitcompose.utils.SharedUtils.SharedListBoxContainer
import com.anibalbastias.uikitcompose.utils.SharedUtils.SharedListElementContainer
import com.anibalbastias.uikitcompose.utils.rememberForeverLazyListState
import com.mxalbert.sharedelements.LocalSharedElementsRootScope
import com.mxalbert.sharedelements.SharedElementsRootScope
import com.skydoves.landscapist.glide.GlideImage

@ExperimentalFoundationApi
@Composable
fun PopularMoviesView(
    nowPlayingState: UiMovieDataState,
    moviesListItems: LazyPagingItems<UiMovieItem>,
    movieDetailAction: (movie: UiMovieItem) -> Unit,
    movieFavoriteAction: (movie: UiMovieItem, isFavorite: Boolean) -> Unit,
) {
    val lazyListState = rememberForeverLazyListState(key = "PopularMovies")
    val scope = LocalSharedElementsRootScope.current!!

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

                itemsIndexed(moviesListItems) { index, item ->
                    if (item != null) {
                        FavoriteSwipeCard(item.isFavorite,
                            screenItem = { isFavorite ->
                                item.isFavorite = isFavorite

                                MovieListItemView(
                                    index = index,
                                    movie = item,
                                    movieDetailAction = movieDetailAction,
                                    scope = scope
                                )
                            },
                            onSwipe = { isFavorite -> movieFavoriteAction(item, isFavorite) }
                        )
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
    movieDetailAction: (movie: UiMovieItem) -> Unit,
    scope: SharedElementsRootScope,
    index: Int,
) {
    Column(
        modifier = Modifier
            .clickable(
                enabled = !scope.isRunningTransition
            ) {
                with(SharedUtils) {
                    scope.changeItem(
                        index,
                        movie.posterPath,
                        movie.originalTitle,
                        movie.releaseDate
                    )
                }
                movieDetailAction.invoke(movie)
            }
    ) {
        Row(
            modifier = Modifier
                .background(color = colorResource(id = R.color.backgroundColor))
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            ConstraintLayout(
                modifier = Modifier.width(70.dp)
            ) {
                val (image, favorite) = createRefs()

                SharedListBoxContainer(movie.posterPath + index) {
                    GlideImage(
                        imageModel = movie.posterPath.replace("/w300/",
                            "/w154/"),
                        modifier = Modifier
                            .width(60.dp)
                            .height(100.dp)
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )
                }

                this@Row.AnimatedVisibility(visible = movie.isFavorite) {
                    Icon(
                        Icons.Default.Favorite,
                        tint = Color.Red,
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .padding(start = 50.dp)
                            .constrainAs(favorite) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(image.end)
                            }
                    )
                }
            }

            Column(modifier = Modifier
                .padding(10.dp)
                .weight(1f)
            ) {
                SharedListElementContainer(movie.originalTitle + index) {
                    Body1(text = movie.originalTitle, color = colorResource(id = R.color.textColor))
                }
                SharedListElementContainer(movie.releaseDate + index) {
                    Body2(text = movie.releaseDate, color = colorResource(id = R.color.textColor))
                }
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
                releaseDate = "March 30, 2022",
                isFavorite = true
            )

            MovieListItemView(movie, {}, LocalSharedElementsRootScope.current!!, 0)
        }
    }
}