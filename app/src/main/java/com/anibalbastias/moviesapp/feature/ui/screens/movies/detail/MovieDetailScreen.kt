@file:OptIn(ExperimentalMotionApi::class)

package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieVideoItem
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.moviesapp.feature.ui.navigation.AppTopBar
import com.anibalbastias.moviesapp.feature.ui.navigation.TopBarType
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.ErrorView
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.LoadingView
import com.anibalbastias.uikitcompose.components.atom.*
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.anibalbastias.uikitcompose.utils.SharedUtils.SharedDetailBoxContainer
import com.anibalbastias.uikitcompose.utils.SharedUtils.SharedDetailElementContainer
import com.google.accompanist.flowlayout.FlowRow
import me.onebone.toolbar.*

val selectedVideo = mutableStateOf(UiMovieVideoItem())
val isShowVideo = mutableStateOf(false)

@ExperimentalMaterialApi
@Composable
fun MovieDetailScreen(
    moviesViewModel: MoviesViewModel,
    movieId: Int?,
    index: Int,
    movieActions: Actions,
) {
    val detailState = moviesViewModel.detailMovies.collectAsState().value
    moviesViewModel.getMovieDetail(movieId = movieId.toString())

    DetailMoviesViewContent(
        detailState,
        index,
        movieActions
    )
}

@ExperimentalMaterialApi
@Composable
fun DetailMoviesViewContent(
    state: APIState<UiMovieDetail>,
    index: Int,
    movieActions: Actions
) {
    when (state) {
        is APIState.Empty -> ErrorView(state.error) {}
        is APIState.Error -> ErrorView(state.error) {}
        APIState.Loading -> LoadingView()
        is APIState.Success -> MovieDetailSuccessView(
            state.data,
            index,
            movieActions
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun MovieDetailSuccessView(
    movie: UiMovieDetail,
    index: Int,
    movieActions: Actions
) {
    Scaffold(
        topBar = {
            if (isShowVideo.value) {
                AppTopBar(
                    type = TopBarType.MOVIE_DETAILS_VIDEO,
                    onChevronClick = { isShowVideo.value = !isShowVideo.value }
                )
            } else {
                AppTopBar(
                    type = TopBarType.MOVIE_DETAILS,
                    onBackClick = { movieActions.goBackAction() }
                )
            }
        },
        content = {
            MovieDetailsContent(movie, index)
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun MovieDetailsContent(
    movie: UiMovieDetail,
    index: Int
) {
    val state = rememberCollapsingToolbarScaffoldState()

    Box {
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.EnterAlways,
            toolbarModifier = Modifier.background(color = colorResource(id = R.color.backgroundColor)),
            toolbar = {
                ExpandableToolbar(movie, state, index)
            }
        ) {
            ScrollableContent(movie, index, selectedVideo)
        }
    }

    if (selectedVideo.value.id.isNotEmpty()) {
        ShowYouTubeVideo(
            title = movie.originalTitle,
            video = selectedVideo.value,
            videos = movie.videos.map { Pair(it.name, it.key) },
            closeButtonAction = {
                selectedVideo.value = UiMovieVideoItem()
            },
            isShowVideo = isShowVideo
        )
    } else {
        null
    }
}

@Composable
fun ScrollableContent(
    movie: UiMovieDetail,
    index: Int,
    selectedVideo: MutableState<UiMovieVideoItem>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = colorResource(id = R.color.backgroundColor))
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 150.dp)
            .verticalScroll(rememberScrollState())
    ) {
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

        Subtitle2(
            text = movie.runtime,
            color = colorResource(id = R.color.textColor),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 20.dp)
        )

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

        HeadlineH6(
            text = stringResource(id = R.string.videos),
            color = colorResource(id = R.color.textColor)
        )

        MovieVideoScreen(selectedVideo, movie)
    }
}

@Composable
fun CollapsingToolbarScope.ExpandableToolbar(
    movie: UiMovieDetail,
    state: CollapsingToolbarScaffoldState,
    index: Int,
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )

    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(movie.backdropPath)
            .crossfade(true)
            .build(),
        contentDescription = movie.originalTitle,
        modifier = Modifier
            .parallax(0.5f)
            .height(280.dp)
            .graphicsLayer {
                // change alpha of Image as the toolbar expands
                alpha = state.toolbarState.progress
            },
        contentScale = ContentScale.Crop
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 100.dp)
    ) {
        SharedDetailBoxContainer(movie.posterPath + index) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(movie.posterPath)
                    .crossfade(true)
                    .build(),
                contentDescription = movie.originalTitle,
                modifier = Modifier
                    .height(200.dp)
            )
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

@ExperimentalMaterialApi
@Preview
@Composable
fun MovieDetailSuccessViewPreview() {
    Surface {
        UIKitComposeTheme {
            val movie = UiMovieDetail(
                id = 1,
                posterPath = "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg",
                backdropPath = "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg",
                originalTitle = "The Mask",
                releaseDate = "March 30, 2022",
                runtime = "2h 2m",
                overview = stringResource(id = R.string.lorem),
                genres = listOf("Action", "Drama"),
                videos = listOf()
            )
            MovieDetailSuccessView(movie,
                0,
                Actions(rememberNavController()))
        }
    }
}