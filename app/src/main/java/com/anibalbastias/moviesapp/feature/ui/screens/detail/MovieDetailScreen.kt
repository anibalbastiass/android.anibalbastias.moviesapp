package com.anibalbastias.moviesapp.feature.ui.screens.detail

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
import com.anibalbastias.uikitcompose.utils.SharedUtils.SharedDetailBoxContainer
import com.anibalbastias.uikitcompose.utils.SharedUtils.SharedDetailElementContainer
import com.anibalbastias.uikitcompose.utils.SharedUtils.changeItem
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.screens.list.state.ErrorView
import com.anibalbastias.moviesapp.feature.ui.screens.list.state.LoadingView
import com.google.accompanist.flowlayout.FlowRow
import com.mxalbert.sharedelements.LocalSharedElementsRootScope

@Composable
fun MovieDetailScreen(
    moviesViewModel: MoviesViewModel,
    movieId: Int?,
    onBack: () -> Unit,
    index: Int,
) {
    val detailState = moviesViewModel.detailMovies.collectAsState().value
    moviesViewModel.getMovieDetail(movieId = movieId.toString())

    val scope = LocalSharedElementsRootScope.current!!

    Scaffold(
        backgroundColor = colorResource(id = R.color.backgroundColor),
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.backgroundColor),
                navigationIcon = {
                    IconButton(onClick = {
                        scope.changeItem(-1)
                        onBack.invoke()
                    }) {
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
            DetailMoviesViewContent(detailState, index)
        }
    )
}

@Composable
fun DetailMoviesViewContent(state: APIState<UiMovieDetail>, index: Int) {
    when (state) {
        is APIState.Empty -> ErrorView(state.error) {}
        is APIState.Error -> ErrorView(state.error) {}
        APIState.Loading -> LoadingView()
        is APIState.Success -> MovieDetailSuccessView(state.data, index)
    }
}

@Composable
fun MovieDetailSuccessView(movie: UiMovieDetail, index: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = colorResource(id = R.color.backgroundColor))
            .fillMaxWidth()
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
            HeadlineH4(text = movie.originalTitle, color = colorResource(id = R.color.textColor))
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

@Composable
fun ReleaseDateText(releaseDate: String) {
    HeadlineH6(text = releaseDate, color = colorResource(id = R.color.textColor))
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
            MovieDetailSuccessView(movie, 0)
        }
    }
}