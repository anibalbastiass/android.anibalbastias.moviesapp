package com.backbase.assignment.feature.domain.repository

import androidx.paging.PagingSource
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.domain.DomainMovieDataState
import com.backbase.assignment.feature.domain.DomainMovieDetailDataState
import kotlinx.coroutines.flow.Flow

interface RemoteMoviesRepository {

    /**
     * Load simple list of Movies by Now Playing label
     */
    suspend fun getNowPlaying(): Flow<DomainMovieDataState>

    /**
     * Create a data source that returns pages of results keyed by page.
     */
    fun getPopularMovies(): PagingSource<Int, EntityMovieItem>

    /**
     * Load Movie detail data
     * @param movieId Id of movie
     */
    suspend fun getMovieById(movieId: String): Flow<DomainMovieDetailDataState>
}