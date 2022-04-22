package com.anibalbastias.moviesapp.feature.domain.repository

import androidx.paging.DataSource
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDataState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDetailDataState
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

    /**
     * Load Movie detail data
     * @param movieId Id of movie
     */
    suspend fun getMovieById(movieId: String): Flow<DomainMovieDetailDataState>
}