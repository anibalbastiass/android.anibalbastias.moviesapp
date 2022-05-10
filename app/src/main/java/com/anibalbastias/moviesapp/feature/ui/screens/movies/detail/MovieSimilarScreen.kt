package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.data.remote.mapper.getUrlMovieImage
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem
import com.anibalbastias.moviesapp.feature.ui.navigation.Actions
import com.anibalbastias.uikitcompose.components.atom.HeadlineH6
import com.anibalbastias.uikitcompose.utils.SharedUtils

@Composable
fun MovieSimilarScreen(
    similar: List<UiMovieItem>,
    movieActions: Actions,
) {
    HeadlineH6(
        text = stringResource(id = R.string.similar_movies),
        color = colorResource(id = R.color.textColor)
    )

    LazyRow(
        content = {
            itemsIndexed(similar) { index, item ->
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(120.dp)
                        .height(170.dp)
                        .clickable { movieActions.movieDetailByMovieAction(item) }
                ) {
                    Column {
                        SharedUtils.SharedDetailBoxContainer(item.posterPath + index) {
                            Image(
                                painter = rememberAsyncImagePainter(getUrlMovieImage(item.posterPath)),
                                contentDescription = item.originalTitle,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Black)
                                    .height(120.dp)
                            )
                        }

                        Text(
                            text = item.originalTitle,
                            style = MaterialTheme.typography.caption,
                            color = colorResource(id = R.color.textColor),
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
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