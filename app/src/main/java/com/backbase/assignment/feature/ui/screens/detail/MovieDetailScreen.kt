package com.backbase.assignment.feature.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.components.atom.HeadlineH4
import com.anibalbastias.uikitcompose.components.atom.HeadlineH6
import com.backbase.assignment.R
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.presentation.model.UiMovieDetail
import com.backbase.assignment.feature.presentation.viewmodel.MoviesViewModel
import com.backbase.assignment.feature.ui.screens.list.state.ErrorView
import com.backbase.assignment.feature.ui.screens.list.state.LoadingView

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
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(movie.posterPath)
                .crossfade(true)
                .build(),
            contentDescription = movie.originalTitle,
            modifier = Modifier
                .height(250.dp)
        )

        HeadlineH4(text = movie.originalTitle, color = colorResource(id = R.color.textColor))
        HeadlineH6(text = movie.releaseDate, color = colorResource(id = R.color.textColor))

        Body1(
            text = movie.overview,
            color = colorResource(id = R.color.textColor),
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}
