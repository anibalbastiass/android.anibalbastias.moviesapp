package com.backbase.assignment.feature.domain.repository

import androidx.paging.DataSource
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.domain.DomainMovieDataState
import com.backbase.assignment.feature.presentation.model.UiMovieItem
import kotlinx.coroutines.flow.Flow

interface RemoteMoviesRepository {

    /**
     * Load simple list of Movies by Now Playing label
     */
    suspend fun getNowPlaying(): Flow<DomainMovieDataState>

    /**
     * Load a page of results from remote data source
     *
     * @param pageToLoad page index
     * @param pageSize size of page
     */
    suspend fun loadPageOfMovies(pageToLoad: Int, pageSize: Int): List<EntityMovieItem>

    /**
     * Create a data source that returns pages of results keyed by page.
     */
    fun getPopularMovies(): DataSource.Factory<Int, EntityMovieItem>
}