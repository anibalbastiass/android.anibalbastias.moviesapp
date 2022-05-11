package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.uikitcompose.components.atom.*
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeViewModel
import com.anibalbastias.uikitcompose.utils.SharedUtils
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovieDetailContentScreen(
    movie: UiMovieDetail,
    index: Int,
    youTubeViewModel: YouTubeViewModel,
    movieActions: Actions,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = colorResource(id = R.color.backgroundColor))
            .padding(16.dp)
    ) {
        item {
            if (index == -1) {
                TitleMovie(movie)
            } else {
                SharedUtils.SharedDetailElementContainer(movie.originalTitle.value + index) {
                    TitleMovie(movie)
                }
            }
        }

        item {
            if (movie.releaseDate.isNotEmpty()) {
                if (index == -1) {
                    ReleaseDateText(movie.releaseDate)
                } else {
                    SharedUtils.SharedDetailElementContainer(movie.releaseDate + index) {
                        ReleaseDateText(movie.releaseDate)
                    }
                }
            } else {
                ReleaseDateText(movie.releaseDate)
            }
        }

        item {
            Subtitle2(
                text = movie.runtime,
                color = colorResource(id = R.color.textColor),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }

        item {
            Body1(
                text = movie.overview.value,
                color = colorResource(id = R.color.textColor),
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }

        item {
            FlowRow(modifier = Modifier.padding(bottom = 50.dp)) {
                movie.genres.map { genre ->
                    Card(
                        modifier = Modifier.padding(5.dp),
                        backgroundColor = colorResource(id = R.color.white)
                    ) {
                        Body2(
                            text = genre,
                            modifier = Modifier.padding(5.dp),
                            color = colorResource(id = R.color.backgroundColor)
                        )
                    }
                }
            }
        }

        item { MovieSimilarScreen(movie.similar, movieActions) }
        item { MovieCreditScreen(movie.credits, movieActions, movie) }
        item { MovieVideoScreen(youTubeViewModel) }
    }
}

@Composable
fun TitleMovie(movie: UiMovieDetail) {
    HeadlineH4(
        text = movie.originalTitle.value,
        color = colorResource(id = R.color.textColor),
        textAlign = TextAlign.Center
    )
}

@Composable
fun ReleaseDateText(releaseDate: String) {
    HeadlineH6(
        text = releaseDate,
        color = colorResource(id = R.color.textColor),
        textAlign = TextAlign.Center
    )
}