package com.backbase.assignment.feature.ui.screens.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.UiMovieDataState
import com.backbase.assignment.feature.presentation.model.UiMovieItem
import com.backbase.assignment.feature.ui.screens.list.state.ErrorView
import com.backbase.assignment.feature.ui.screens.list.state.LoadingView

@Composable
fun NowPlayingMoviesView(state: UiMovieDataState, movieDetailAction: (movieId: Int) -> Unit) {
    when (state) {
        is APIState.Empty -> ErrorView(state.error) {}
        is APIState.Error -> ErrorView(state.error) {}
        APIState.Loading -> LoadingView()
        is APIState.Success -> NowPlayingViewSuccess(state.data, movieDetailAction)
    }
}

@Composable
fun NowPlayingViewSuccess(data: List<UiMovieItem>, movieDetailAction: (movieId: Int) -> Unit) {
    Column {
        LazyRow {
            items(data) { movie ->
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.posterPath)
                        .crossfade(true)
                        .build(),
                    contentDescription = movie.originalTitle,
                    modifier = Modifier
                        .height(180.dp)
                        .clickable { movieDetailAction.invoke(movie.id.toInt()) },
                )
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
                    releaseDate = "March 30, 2022"
                )
            }
            NowPlayingViewSuccess(data) {}
        }
    }
}