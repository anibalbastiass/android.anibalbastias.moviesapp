package com.backbase.assignment

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.backbase.assignment.feature.ui.list.MovieListFragment
import com.backbase.assignment.utils.launchFragmentInHiltContainer
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesActivityTest {

    @Test
    fun testNavigationToInMovieListFragment() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        launchFragmentInHiltContainer<MovieListFragment, MoviesActivity> {
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.app_nav_graph)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        assertEquals(navController.currentDestination?.id, R.id.nav_movie_list)
    }
}