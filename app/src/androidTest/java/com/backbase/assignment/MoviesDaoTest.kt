package com.backbase.assignment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.LivePagedListBuilder
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.utils.getFirst
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
    fun insertAlbumTest() = runBlocking {
        // Given
        val moviesDao = appDatabase.moviesDao()
        val movies = mutableListOf<EntityMovieItem>()
        for (num in 0 until 10) {
            movies.add(EntityMovieItem())
        }

        // When
        val liveData = LivePagedListBuilder(moviesDao.getMovies(), 10).build()

        // Then
        moviesDao.insert(movies)
        assertEquals(liveData.getFirst().size, 1)

        moviesDao.clearMovies()
        assertEquals(liveData.getFirst().size, 0)
    }
}