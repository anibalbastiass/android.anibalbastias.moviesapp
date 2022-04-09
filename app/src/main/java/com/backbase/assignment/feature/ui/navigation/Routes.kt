package com.backbase.assignment.feature.ui.navigation

import com.backbase.assignment.feature.ui.navigation.Actions.Companion.MOVIE_ID_KEY

enum class Routes(val route: String) {
    MOVIES_LIST("movies"),
    MOVIES_DETAIL("movies/{$MOVIE_ID_KEY}")
}