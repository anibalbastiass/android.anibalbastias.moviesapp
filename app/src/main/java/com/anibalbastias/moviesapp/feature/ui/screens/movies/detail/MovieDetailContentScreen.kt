package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.anibalbastias.uikitcompose.components.atom.HeadlineH4
import com.anibalbastias.uikitcompose.components.atom.HeadlineH6
import com.anibalbastias.uikitcompose.components.atom.Subtitle2
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
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 150.dp)
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