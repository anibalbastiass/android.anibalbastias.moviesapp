package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.moviesapp.feature.ui.navigation.AppTopBar
import com.anibalbastias.moviesapp.feature.ui.navigation.TopBarType
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.ErrorView
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.LoadingView
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.components.atom.HeadlineH4
import com.anibalbastias.uikitcompose.components.atom.HeadlineH6
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.anibalbastias.uikitcompose.utils.SharedUtils.SharedDetailBoxContainer
import com.anibalbastias.uikitcompose.utils.SharedUtils.SharedDetailElementContainer
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovieDetailScreen(
    moviesViewModel: MoviesViewModel,
    movieId: Int?,
    index: Int,
    movieActions: Actions,
) {
    val detailState = moviesViewModel.detailMovies.collectAsState().value
    moviesViewModel.getMovieDetail(movieId = movieId.toString())

    DetailMoviesViewContent(detailState, index, movieActions)
}

@Composable
fun DetailMoviesViewContent(state: APIState<UiMovieDetail>, index: Int, movieActions: Actions) {
    when (state) {
        is APIState.Empty -> ErrorView(state.error) {}
        is APIState.Error -> ErrorView(state.error) {}
        APIState.Loading -> LoadingView()
        is APIState.Success -> MovieDetailSuccessView(state.data, index, movieActions)
    }
}

@Composable
fun MovieDetailSuccessView(movie: UiMovieDetail, index: Int, movieActions: Actions) {
    Scaffold(
        topBar = {
            AppTopBar(
                type = TopBarType.MOVIE_DETAILS,
                onBackClick = { movieActions.goBackAction() }
            )
        },
        content = {
            MovieDetailsContent(movie, index)
        }
    )
}

@Composable
fun MovieDetailsContent(movie: UiMovieDetail, index: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = colorResource(id = R.color.backgroundColor))
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SharedDetailBoxContainer(movie.posterPath + index) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.originalTitle,
                modifier = Modifier
                    .width(180.dp)
                    .height(250.dp)
            )
        }

        SharedDetailElementContainer(movie.originalTitle + index) {
            HeadlineH4(
                text = movie.originalTitle,
                color = colorResource(id = R.color.textColor),
                textAlign = TextAlign.Center
            )
        }

        if (movie.releaseDate.isNotEmpty()) {
            SharedDetailElementContainer(movie.releaseDate + index) {
                ReleaseDateText(movie.releaseDate)
            }
        } else {
            ReleaseDateText(movie.releaseDate)
        }

        Body1(
            text = movie.overview,
            color = colorResource(id = R.color.textColor),
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(vertical = 20.dp)
        )

        FlowRow(modifier = Modifier.padding(bottom = 50.dp)) {
            movie.genres.map { genre ->
                Body1(
                    text = genre,
                    color = colorResource(id = R.color.backgroundColor),
                    modifier = Modifier
                        .padding(5.dp)
                        .background(color = colorResource(id = R.color.white))
                        .padding(10.dp)
                )
            }
        }
    }
}

@Composable
fun ReleaseDateText(releaseDate: String) {
    HeadlineH6(
        text = releaseDate,
        color = colorResource(id = R.color.textColor),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun MovieDetailSuccessViewPreview() {
    Surface {
        UIKitComposeTheme {
            val movie = UiMovieDetail(
                id = 1,
                posterPath = "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg",
                originalTitle = "The Mask",
                releaseDate = "March 30, 2022",
                overview = stringResource(id = R.string.lorem),
                genres = listOf("Action", "Drama")
            )
            MovieDetailSuccessView(movie, 0, Actions(rememberNavController()))
        }
    }
}