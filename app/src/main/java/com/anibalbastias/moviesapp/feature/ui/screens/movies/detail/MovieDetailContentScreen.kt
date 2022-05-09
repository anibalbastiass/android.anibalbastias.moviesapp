package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.anibalbastias.uikitcompose.components.atom.*
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeViewModel
import com.anibalbastias.uikitcompose.utils.SharedUtils
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovieDetailContentScreen(
    movie: UiMovieDetail,
    index: Int,
    youTubeViewModel: YouTubeViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = colorResource(id = R.color.backgroundColor))
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SharedUtils.SharedDetailElementContainer(movie.originalTitle + index) {
            HeadlineH4(
                text = movie.originalTitle,
                color = colorResource(id = R.color.textColor),
                textAlign = TextAlign.Center
            )
        }

        if (movie.releaseDate.isNotEmpty()) {
            SharedUtils.SharedDetailElementContainer(movie.releaseDate + index) {
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

        MovieSimilarScreen(movie.similar)
        MovieCreditScreen(movie.credits)
        MovieVideoScreen(youTubeViewModel)
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