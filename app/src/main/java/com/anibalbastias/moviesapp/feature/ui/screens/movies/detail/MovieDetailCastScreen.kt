package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import com.anibalbastias.moviesapp.feature.presentation.model.UiMoviePerson
import com.anibalbastias.moviesapp.feature.presentation.viewmodel.MoviesViewModel
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.ErrorView
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.state.LoadingView
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.components.atom.Subtitle2
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailCastScreen(
    personId: String,
    moviesViewModel: MoviesViewModel = hiltViewModel(),
) {
    val personState = moviesViewModel.personMovies.collectAsState().value
    moviesViewModel.getMoviePerson(personId)

    PersonMoviesViewContent(personState)
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
        modifier = Modifier
//            .wrapContentSize(unbounded = true)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 120.dp)
    ) {
        item {
            GlideImage(
                imageModel = person.profilePath,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(180.dp)
                    .height(280.dp),
            )

            Body1(text = person.name)
            Body1(text = person.gender)
            Body1(text = person.birthday)
            Body1(text = person.deathDay)
            Body1(text = person.placeOfBirth)
            Body1(text = person.knownForDepartment)
            Body1(text = person.biography)
        }

        item {
            Subtitle2(text = "Known for")
            CreditList(person.knownFor)
        }

        item {
            Subtitle2(text = "Acting")
            CreditList(person.acting)
        }

        item {
            Subtitle2(text = "Write")
            CreditList(person.writing)
        }

        item {
            Subtitle2(text = "Director")
            CreditList(person.directing)
        }

        item {
            Subtitle2(text = "Production")
            CreditList(person.production)
        }

        item {
            Subtitle2(text = "Creation")
            CreditList(person.creator)
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
