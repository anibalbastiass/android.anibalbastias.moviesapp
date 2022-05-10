@file:OptIn(ExperimentalMotionApi::class)

package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.presentation.model.*
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.moviesapp.feature.ui.navigation.AppTopBar
import com.anibalbastias.moviesapp.feature.ui.navigation.TopBarType
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.ErrorView
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.LoadingView
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeExpandableScreen
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeViewModel
import com.anibalbastias.uikitcompose.components.molecules.youtube.model.YouTubeVideoItem
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import kotlinx.coroutines.launch
import me.onebone.toolbar.*

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MovieDetailScreen(
    moviesViewModel: MoviesViewModel,
    youTubeViewModel: YouTubeViewModel,
    movieId: Int?,
    index: Int = -1,
    movieActions: Actions,
) {
    val detailState = moviesViewModel.detailMovies.collectAsState().value
    moviesViewModel.getMovieDetail(movieId = movieId.toString())

    DetailMoviesViewContent(
        detailState,
        index,
        movieActions,
        youTubeViewModel
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun DetailMoviesViewContent(
    state: APIState<UiMovieDetail>,
    index: Int,
    movieActions: Actions,
    youTubeViewModel: YouTubeViewModel,
) {
    when (state) {
        APIState.Loading -> LoadingView()
        is APIState.Empty -> ErrorView(state.error) {}
        is APIState.Error -> ErrorView(state.error) {}
        is APIState.Success -> MovieDetailSuccessView(
            state.data,
            index,
            movieActions,
            youTubeViewModel
        )
    }
}

@ExperimentalFoundationApi
@ExperimentalMotionApi
@ExperimentalMaterialApi
@Composable
fun MovieDetailSuccessView(
    movie: UiMovieDetail,
    index: Int,
    movieActions: Actions,
    youTubeViewModel: YouTubeViewModel,
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        topBar = {
            if (youTubeViewModel.isExpanded.value) {
                AppTopBar(
                    type = TopBarType.MOVIE_DETAILS_VIDEO,
                    onChevronClick = {
                        youTubeViewModel.isExpanded.value = !youTubeViewModel.isExpanded.value
                    }
                )
            } else {
                AppTopBar(
                    type = TopBarType.MOVIE_DETAILS,
                    onBackClick = {
                        youTubeViewModel.reset()
                        movieActions.goBackAction()
                    },
                    onChangeLanguage = {
                        coroutineScope.launch {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        }
                    }
                )
            }
        },
        content = {
            MovieDetailsContent(movie, index, youTubeViewModel, movieActions)
        },
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Box(
                Modifier
                    .background(colorResource(id = R.color.backgroundSecondaryColor))
                    .fillMaxWidth()
                    .height(400.dp)
            ) {
                MovieDetailTranslations(
                    translations = movie.translations,
                    coroutineScope = coroutineScope,
                    bottomSheetScaffoldState = bottomSheetScaffoldState,
                    onUpdateLanguage = { translation ->
                        movie.originalTitle.value =
                            translation.translationData.title.ifEmpty { movie._originalTitle }
                        movie.overview.value =
                            translation.translationData.overview.ifEmpty { movie._overview }
                    }
                )
            }
        }
    )
}

@ExperimentalMotionApi
@ExperimentalMaterialApi
@Composable
fun MovieDetailsContent(
    movie: UiMovieDetail,
    index: Int,
    youTubeViewModel: YouTubeViewModel,
    movieActions: Actions,
) {
    val state = rememberCollapsingToolbarScaffoldState()

    Box {
        CollapsingToolbarScaffold(
            modifier = Modifier.fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbarModifier = Modifier.background(color = colorResource(id = R.color.backgroundColor)),
            toolbar = {
                MovieDetailToolBarScreen(movie, state, index)
            }
        ) {
            MovieDetailContentScreen(movie, index, youTubeViewModel, movieActions)
        }

        // Initialize videos
        youTubeViewModel.videos.value = movie.videos.map { video ->
            YouTubeVideoItem(
                key = video.key,
                name = video.name,
                main = movie.originalTitle.value
            )
        }

        if (youTubeViewModel.isShowing.value &&
            youTubeViewModel.previousMovie.value == movie.originalTitle.value
        ) {
            YouTubeExpandableScreen(
                background = colorResource(id = R.color.backgroundSecondaryColorAlpha),
                textColor = colorResource(id = R.color.textColor),
                viewModel = youTubeViewModel,
                closeButtonAction = {
                    youTubeViewModel.reset()
                }
            )
        } else {
            youTubeViewModel.reset()
            null
        }
    }
}

@ExperimentalFoundationApi
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
                _originalTitle = "The Mask",
                releaseDate = "March 30, 2022",
                runtime = "2h 2m",
                _overview = stringResource(id = R.string.lorem),
                genres = listOf("Action", "Drama"),
                videos = listOf(),
                credits = UiMovieCredits(),
                providers = listOf(),
                similar = listOf(),
                translations = listOf()
            )
            MovieDetailSuccessView(movie,
                0,
                Actions(rememberNavController()),
                viewModel()
            )
        }
    }
}