package com.backbase.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.backbase.assignment.feature.ui.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

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

