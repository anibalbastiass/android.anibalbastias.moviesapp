package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.data.remote.mapper.getUrlMovieImage
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieCredits
import com.anibalbastias.uikitcompose.components.atom.HeadlineH6

@Composable
fun MovieCreditScreen(credits: UiMovieCredits) {
    HeadlineH6(
        text = stringResource(id = R.string.cast),
        color = colorResource(id = R.color.textColor),
        modifier = Modifier.padding(top = 30.dp)
    )

    LazyRow(
        content = {
            items(credits.cast) { item ->
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp)
                        .height(250.dp)
                ) {
                    Column {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(getUrlMovieImage(item.profilePath))
                                .crossfade(true)
                                .build(),
                            contentDescription = item.originalName,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .background(Color.Black)
                                .fillMaxWidth()
                                .height(150.dp)
                        )
                        Text(
                            text = item.originalName,
                            style = MaterialTheme.typography.body1,
                            color = colorResource(id = R.color.textColor),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    colorResource(
                                        id = R.color.backgroundSecondaryColor
                                    )
                                )
                                .padding(10.dp)
                        )

                        Text(
                            text = item.character,
                            style = MaterialTheme.typography.body2,
                            color = colorResource(id = R.color.textColor),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    colorResource(
                                        id = R.color.backgroundSecondaryColor
                                    )
                                )
                                .padding(10.dp)
                        )
                    }
                }
            }
        }
    )
}