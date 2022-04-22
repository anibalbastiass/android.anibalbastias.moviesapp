package com.anibalbastias.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.anibalbastias.moviesapp.feature.ui.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagingApi
@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UIKitComposeTheme(
                content = {
                    NavGraph()
                }
            )
        }
    }
}

