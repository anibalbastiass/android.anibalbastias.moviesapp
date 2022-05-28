package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.anibalbastias.moviesapp.R
import com.anibalbastias.uikitcompose.components.atom.HeadlineH6
import com.anibalbastias.uikitcompose.components.pages.youtube.ScrollToSelectedVideo
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeUtils.getYouTubeThumbnail
import com.anibalbastias.uikitcompose.components.molecules.youtube.YouTubeViewModel
import com.anibalbastias.uikitcompose.utils.rememberForeverLazyListState
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun MovieVideoScreen(
    youTubeViewModel: YouTubeViewModel,
) {
    val lazyListState = rememberForeverLazyListState(key = "VideoListMovies")
    val coroutineScope = rememberCoroutineScope()
    val borderColor = colorResource(id = R.color.white)

    HeadlineH6(
        text = stringResource(id = R.string.videos),
        color = colorResource(id = R.color.textColor),
        modifier = Modifier.padding(top = 30.dp)
    )

    LazyRow(
        modifier = Modifier.padding(bottom = 150.dp),
        state = lazyListState,
        content = {
            items(youTubeViewModel.videos) { video ->
                Card(
                    border =
                    if (video.key == youTubeViewModel.selectedVideo.key) {
                        BorderStroke(2.dp, borderColor)
                    } else {
                        null
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp)
                        .height(160.dp)
                        .clickable {
                            coroutineScope.launch {
                                youTubeViewModel.previousMovie = video.main

                                youTubeViewModel.selectedVideo = video
                                youTubeViewModel.isShowing = true

                                youTubeViewModel.isExpanded = false
                                delay(150)
                                youTubeViewModel.isExpanded = true
                            }
                        }
                ) {
                    Column {
                        GlideImage(
                            imageModel = getYouTubeThumbnail(video.key),
                            contentScale = ContentScale.Crop,
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
                            maxLines = 1,
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
        }
    )

    ScrollToSelectedVideo(youTubeViewModel, lazyListState)
}
