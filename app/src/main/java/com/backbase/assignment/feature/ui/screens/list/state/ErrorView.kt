package com.backbase.assignment.feature.ui.screens.list.state

import androidx.compose.runtime.Composable
import com.anibalbastias.uikitcompose.components.templates.ErrorTemplate

@Composable
fun ErrorView(error: String, reloadState: () -> Unit) {
    ErrorTemplate("Empty Error", error, "Reload") {
        reloadState.invoke()
    }
}