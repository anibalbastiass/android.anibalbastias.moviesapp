package com.anibalbastias.moviesapp.feature.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.anibalbastias.moviesapp.R
import com.anibalbastias.uikitcompose.components.molecules.CenterTopAppBar

enum class TopBarType {
    MOVIE_LIST, MOVIE_DETAILS, MOVIE_DETAILS_VIDEO,
    MOVIE_DETAILS_PERSON, FAVORITES
}

@Composable
fun AppTopBar(
    type: TopBarType,
    onBackClick: (() -> Unit)? = null,
    onSearchBarClick: (() -> Unit)? = null,
    onChevronClick: (() -> Unit)? = null,
    onChangeLanguage: (() -> Unit)? = null,
) {
    CenterTopAppBar(
        backgroundColor = colorResource(id = R.color.backgroundColor),
        titleView = {
            Image(
                painterResource(R.drawable.movies_logo),
                contentDescription = "MovieBox",
                modifier = Modifier.height(20.dp)
            )
        },
        navigationIconView = {
            when (type) {
                TopBarType.MOVIE_DETAILS, TopBarType.MOVIE_DETAILS_PERSON -> {
                    IconButton(
                        onClick = { onBackClick!!() },
                        enabled = true,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
                TopBarType.MOVIE_DETAILS_VIDEO -> {
                    IconButton(
                        onClick = { onChevronClick!!() },
                        enabled = true,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron),
                            contentDescription = stringResource(id = R.string.back),
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
                TopBarType.MOVIE_LIST, TopBarType.FAVORITES -> null
            }
        },
        actionsView = {
            when (type) {
                TopBarType.MOVIE_LIST -> {
                    IconButton(
                        onClick = { onSearchBarClick!!() },
                        enabled = true,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = stringResource(id = R.string.search_movies),
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
                TopBarType.MOVIE_DETAILS -> {
                    IconButton(
                        onClick = { onChangeLanguage!!() },
                        enabled = true,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_language),
                            contentDescription = stringResource(id = R.string.change_language),
                            tint = colorResource(id = R.color.white)
                        )
                    }
                }
                TopBarType.FAVORITES, TopBarType.MOVIE_DETAILS_VIDEO -> null
            }
        }
    )
}
