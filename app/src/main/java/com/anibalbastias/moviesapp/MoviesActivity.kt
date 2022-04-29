package com.anibalbastias.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import com.anibalbastias.moviesapp.feature.ui.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagingApi
@AndroidEntryPoint
class MoviesActivity : ComponentActivity() {

    companion object {
        const val splashFadeDurationMillis = 300
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashWasDisplayed = savedInstanceState != null

        if (!splashWasDisplayed) {
            val splashScreen = installSplashScreen()
            splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
                splashScreenViewProvider.iconView
                    .animate()
                    .setDuration(splashFadeDurationMillis.toLong())
                    .alpha(0f)
                    .withEndAction {
                        splashScreenViewProvider.remove()
                        showContent()
                    }.start()
            }
        } else {
            setTheme(R.style.AppTheme)
            showContent()
        }
    }

    private fun showContent() {
        setContent {
            UIKitComposeTheme(
                content = {
                    NavGraph()
                }
            )
        }
    }
}

