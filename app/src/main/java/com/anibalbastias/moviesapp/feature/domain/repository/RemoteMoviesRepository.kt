package com.anibalbastias.moviesapp.feature.domain.repository

import androidx.paging.PagingSource
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
     * Create a data source that returns pages of results keyed by page.
     */
    fun getPopularMovies(): PagingSource<Int, EntityMovieItem>

    /**
     * Load Movie detail data
     * @param movieId Id of movie
     */
    suspend fun getMovieById(movieId: String): Flow<DomainMovieDetailDataState>
}