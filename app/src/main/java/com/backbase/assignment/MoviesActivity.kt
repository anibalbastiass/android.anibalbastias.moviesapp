package com.backbase.assignment

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.backbase.assignment.feature.presentation.viewmodel.MoviesPagedViewModel
import com.backbase.assignment.feature.presentation.viewmodel.MoviesViewModel
import com.backbase.assignment.feature.ui.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private val moviesPagedViewModel: MoviesPagedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UIKitComposeTheme(
                content = {
                    NavGraph(
                        moviesViewModel = moviesViewModel,
                        moviesPagedViewModel = moviesPagedViewModel
                    )
                }
            )
        }
    }
}

