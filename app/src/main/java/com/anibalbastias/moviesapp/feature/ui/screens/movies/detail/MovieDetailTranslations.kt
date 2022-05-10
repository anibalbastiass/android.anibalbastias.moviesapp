package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieTranslationItem
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.StickyHeaderMovie
import com.anibalbastias.uikitcompose.components.atom.Body1
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MovieDetailTranslations(
    translations: List<UiMovieTranslationItem>,
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    onUpdateLanguage: (translation: UiMovieTranslationItem) -> Unit,
) {
    LazyColumn(
        content = {
            stickyHeader {
                StickyHeaderMovie(title = stringResource(id = R.string.translations))
            }
            items(translations) { item ->
                Row(
                    modifier = Modifier
                        .clickable {
                            onUpdateLanguage(item)
                            coroutineScope.launch {
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            }
                        }
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            "https://flagcdn.com/32x24/${item.iso31661.toLowerCase()}.png"
                        ),
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(start = 10.dp, end = 10.dp)
                    )

                    Body1(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 12.dp),
                        color = colorResource(id = R.color.textColor),
                        text = item.englishName
                    )
                }
            }
        })
}