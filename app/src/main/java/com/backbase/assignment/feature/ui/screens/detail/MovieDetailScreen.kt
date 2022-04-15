package com.backbase.assignment.feature.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.components.atom.HeadlineH4
import com.anibalbastias.uikitcompose.components.atom.HeadlineH6
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.backbase.assignment.R
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.presentation.model.UiMovieDetail
import com.backbase.assignment.feature.presentation.viewmodel.MoviesViewModel
import com.backbase.assignment.feature.ui.screens.list.state.ErrorView
import com.backbase.assignment.feature.ui.screens.list.state.LoadingView
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovieDetailScreen(
    moviesViewModel: MoviesViewModel,
    movieId: Int?,
    onBack: () -> Unit,
) {
    val detailState = moviesViewModel.detailMovies.collectAsState().value
    moviesViewModel.getMovieDetail(movieId = movieId.toString())

    Scaffold(
        backgroundColor = colorResource(id = R.color.backgroundColor),
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.backgroundColor),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            tint = colorResource(id = R.color.white),
                            contentDescription = "backIcon"
                        )
                    }
                },
                title = {}
            )
        },
        content = {
            DetailMoviesViewContent(detailState)
        }
    )
}

@Composable
fun DetailMoviesViewContent(state: APIState<UiMovieDetail>) {
    when (state) {
        is APIState.Empty -> ErrorView(state.error) {}
        is APIState.Error -> ErrorView(state.error) {}
        APIState.Loading -> LoadingView()
        is APIState.Success -> MovieDetailSuccessView(state.data)
    }
}

@Composable
fun MovieDetailSuccessView(movie: UiMovieDetail) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = colorResource(id = R.color.backgroundColor))
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
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

        HeadlineH4(text = movie.originalTitle, color = colorResource(id = R.color.textColor))
        HeadlineH6(text = movie.releaseDate, color = colorResource(id = R.color.textColor))

        Body1(
            text = movie.overview,
            color = colorResource(id = R.color.textColor),
            modifier = Modifier.padding(vertical = 20.dp)
        )

        FlowRow {
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
            MovieDetailSuccessView(movie)
        }
    }
}