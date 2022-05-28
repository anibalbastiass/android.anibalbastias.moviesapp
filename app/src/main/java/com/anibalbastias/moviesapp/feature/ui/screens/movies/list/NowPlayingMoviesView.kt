package com.anibalbastias.moviesapp.feature.ui.screens.movies.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.domain.UiMovieDataState
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.ErrorView
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.LoadingView
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.anibalbastias.uikitcompose.utils.SharedUtils.SharedListElementContainer
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NowPlayingMoviesView(
    state: UiMovieDataState,
    movieDetailAction: (movie: UiMovieItem) -> Unit,
) {
    when (state) {
        is APIState.Empty -> ErrorView(state.error) {}
        is APIState.Error -> ErrorView(state.error) {}
        APIState.Loading -> LoadingView(Modifier.background(colorResource(id = R.color.backgroundColor)))
        is APIState.Success -> NowPlayingViewSuccess(state.data, movieDetailAction)
    }
}

@Composable
fun NowPlayingViewSuccess(
    data: List<UiMovieItem>,
    movieDetailAction: (movie: UiMovieItem) -> Unit,
) {
    Column {
        LazyRow {
            itemsIndexed(data) { index, movie ->
                SharedListElementContainer(movie.posterPath + index) {
                    GlideImage(
                        imageModel = movie.posterPath.replace("/w300/",
                            "/w154/"),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(120.dp)
                            .height(180.dp)
                            .clickable {
                                movieDetailAction.invoke(movie)
                            },
                        shimmerParams = ShimmerParams(
                            baseColor = MaterialTheme.colors.background,
                            highlightColor = colorResource(id = R.color.textColor),
                            durationMillis = 350,
                            dropOff = 0.65f,
                            tilt = 20f
                        ),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NowPlayingViewSuccessPreview() {
    Surface {
        UIKitComposeTheme {
            val data = (0..10).map {
                UiMovieItem(
                    id = 1L,
                    posterPath = "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg",
                    originalTitle = "The Mask",
                    voteAverage = 8.5,
                    releaseDate = "March 30, 2022",
                    isFavorite = true
                )
            }
            NowPlayingViewSuccess(data) {}
        }
    }
}