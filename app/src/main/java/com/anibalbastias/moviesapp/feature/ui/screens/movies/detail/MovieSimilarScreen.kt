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
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import com.anibalbastias.uikitcompose.components.atom.HeadlineH6

@Composable
fun MovieSimilarScreen(similar: List<UiMovieItem>) {
    HeadlineH6(
        text = stringResource(id = R.string.similar_movies),
        color = colorResource(id = R.color.textColor)
    )

    LazyRow(
        content = {
            items(similar) { item ->
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(150.dp)
                        .height(250.dp)
                ) {
                    Column {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(getUrlMovieImage(item.posterPath))
                                .crossfade(true)
                                .build(),
                            contentDescription = item.originalTitle,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Black)
                                .height(150.dp)
                        )
                        Text(
                            text = item.originalTitle,
                            style = MaterialTheme.typography.body1,
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