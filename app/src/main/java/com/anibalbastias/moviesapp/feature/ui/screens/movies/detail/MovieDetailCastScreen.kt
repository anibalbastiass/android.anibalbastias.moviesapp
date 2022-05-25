package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import com.anibalbastias.moviesapp.feature.presentation.model.UiMoviePerson
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.moviesapp.feature.ui.navigation.AppTopBar
import com.anibalbastias.moviesapp.feature.ui.navigation.TopBarType
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.ErrorView
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.LoadingView
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.components.atom.HeadlineH4
import com.anibalbastias.uikitcompose.components.atom.HeadlineH6
import com.anibalbastias.uikitcompose.components.atom.Subtitle2
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun MovieDetailCastScreen(
    personId: String,
    movieActions: Actions,
    moviesViewModel: MoviesViewModel = hiltViewModel(),
) {
    val personState = moviesViewModel.personMovies.collectAsState().value
    moviesViewModel.getMoviePerson(personId)

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                coroutineScope.launch {
                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            })
        },
        topBar = {
            AppTopBar(
                type = TopBarType.MOVIE_DETAILS_PERSON,
                onBackClick = {
                    movieActions.goBackAction()
                }
            )
        },
        content = {
            PersonMoviesViewContent(personState)
        },
        sheetPeekHeight = 0.dp,
        sheetContent = {
            Box(
                Modifier
                    .background(colorResource(id = R.color.backgroundSecondaryColor))
                    .fillMaxWidth()
                    .height(400.dp)
            ) {

            }
        }
    )
}

@Composable
fun PersonMoviesViewContent(state: APIState<UiMoviePerson>) {
    when (state) {
        APIState.Loading -> LoadingView(Modifier.background(colorResource(id = R.color.backgroundColor)))
        is APIState.Empty -> ErrorView(state.error) {}
        is APIState.Error -> ErrorView(state.error) {}
        is APIState.Success -> MoviePersonSuccessView(state.data)
    }
}

@Composable
fun MoviePersonSuccessView(person: UiMoviePerson) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(colorResource(id = R.color.backgroundColor))
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 50.dp)
            .fillMaxWidth()
    ) {
        item {
            GlideImage(
                imageModel = person.profilePath,
                alignment = Alignment.Center,
                modifier = Modifier
                    .width(150.dp)
                    .height(180.dp)
                    .padding(top = 10.dp),
            )

            HeadlineH4(
                text = person.name,
                color = colorResource(id = R.color.textColor),
                textAlign = TextAlign.Center
            )

            HeadlineH6(
                text = person.birthday,
                color = colorResource(id = R.color.textColor),
                textAlign = TextAlign.Center
            )

            Body1(text = person.deathDay, color = colorResource(id = R.color.textColor))
            Body1(text = person.placeOfBirth, color = colorResource(id = R.color.textColor))
            Body1(text = person.biography,
                color = colorResource(id = R.color.textColor),
                textAlign = TextAlign.Justify)
        }

        item {
            if (person.knownFor.isNotEmpty()) {
                Subtitle2(text = "Known for", color = colorResource(id = R.color.textColor))
                CreditList(person.knownFor)
            }
        }

        item {
            if (person.acting.isNotEmpty()) {
                Subtitle2(text = "Acting", color = colorResource(id = R.color.textColor))
                CreditList(person.acting)
            }
        }

        item {
            if (person.writing.isNotEmpty()) {
                Subtitle2(text = "Write", color = colorResource(id = R.color.textColor))
                CreditList(person.writing)
            }
        }

        item {
            if (person.directing.isNotEmpty()) {
                Subtitle2(text = "Director", color = colorResource(id = R.color.textColor))
                CreditList(person.directing)
            }
        }

        item {
            if (person.production.isNotEmpty()) {
                Subtitle2(text = "Production")
                CreditList(person.production)
            }
        }

        item {
            if (person.creator.isNotEmpty()) {
                Subtitle2(text = "Creation")
                CreditList(person.creator)
            }
        }
    }
}

@Composable
fun CreditList(list: List<UiMovieItem>) {
    Column() {
        list.map { item ->
            Card(
                elevation = 5.dp,
                modifier = Modifier.padding(5.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Body1(text = item.originalTitle)
                    Body1(text = item.releaseDate)
                }
            }
        }
    }
}
