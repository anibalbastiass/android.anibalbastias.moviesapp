package com.anibalbastias.moviesapp.feature.ui.screens.movies.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anibalbastias.moviesapp.R
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieTranslationItem
import com.anibalbastias.moviesapp.feature.ui.screens.movies.list.StickyHeaderMovie
import com.anibalbastias.uikitcompose.components.atom.Body1
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
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
                        .border(
                            BorderStroke(
                                if (item.isSelected) 2.dp else 0.dp,
                                if (item.isSelected) Color.White else Color.Transparent)
                        )
                        .clickable {
                            onUpdateLanguage(item)
                            coroutineScope.launch {
                                translations.map { it.isSelected = false }
                                item.isSelected = true

                                delay(300)
                                bottomSheetScaffoldState.bottomSheetState.collapse()
                            }
                        }
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    GlideImage(
                        imageModel = "https://flagcdn.com/32x24/${item.iso31661.toLowerCase()}.png",
                        contentScale = ContentScale.FillWidth,
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