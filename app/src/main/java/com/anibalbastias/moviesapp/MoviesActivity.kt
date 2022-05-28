package com.anibalbastias.moviesapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.anibalbastias.moviesapp.feature.ui.navigation.NavGraph
import com.anibalbastias.uikitcompose.theme.UIKitComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

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

