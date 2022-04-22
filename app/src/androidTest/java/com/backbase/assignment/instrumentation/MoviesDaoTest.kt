package com.backbase.assignment.instrumentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
open class MoviesDaoTest : DatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertMoviesTest() = runBlocking {
        // Given
        val moviesDao = appDatabase.moviesDao()
        val movies = mutableListOf<EntityMovieItem>()

        for (num in 0 until 10) {
            movies.add(
                EntityMovieItem(
                    id = "1",
                    posterPath = "/7gFo1PEbe1CoSgNTnjCGdZbw0zP.jpg",
                    originalTitle = "The Mask",
                    voteAverage = 8.0,
                    releaseDate = "March 30, 2022"
                )
            )
        }

        // When
        val pagingSource = moviesDao.getAllMovies()

        assertEquals(
            PagingSource.LoadResult.Page(
                data = listOf(movies[0], movies[1]),
                prevKey = null,
                nextKey = movies[1].id
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            ),
        )
    }
}