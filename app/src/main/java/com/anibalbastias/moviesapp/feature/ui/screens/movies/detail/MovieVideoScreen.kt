package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieVideoItem
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeExpandableScreen

@Composable
fun MovieVideoScreen(selectedVideo: MutableState<UiMovieVideoItem>, movie: UiMovieDetail) {
    LazyRow(content = {
        items(movie.videos) { video ->
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .width(150.dp)
                    .height(170.dp)
                    .clickable {
                        selectedVideo.value = video
                    }
            ) {
                Column {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(video.thumbnail)
                            .crossfade(true)
                            .build(),
                        contentDescription = video.name,
                        modifier = Modifier
                            .background(Color.Black)
                            .fillMaxWidth()
                            .height(120.dp)
                    )
                    Text(
                        text = video.name,
                        style = MaterialTheme.typography.caption,
                        color = colorResource(id = R.color.textColor),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        modifier = Modifier
                            .background(
                                colorResource(
                                    id = R.color.backgroundSecondaryColor
                                )
                            )
                            .padding(10.dp)
                            .fillMaxSize()
                    )
                }
            }
        }
    })
}

@ExperimentalMotionApi
@Composable
fun ShowYouTubeVideo(
    title: String,
    video: UiMovieVideoItem,
    description: String
) {
    YouTubeExpandableScreen(
        background = colorResource(id = R.color.backgroundColor),
        textColor = colorResource(id = R.color.textColor),
        title = title,
        subTitle = video.name,
        key = video.key,
        description = description
    )
}