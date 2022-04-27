package com.anibalbastias.moviesapp.feature.domain.repository

import androidx.paging.PagingSource
import com.anibalbastias.moviesapp.feature.data.local.model.EntityFavoriteMovieItem
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import kotlinx.coroutines.flow.Flow

interface LocalMoviesRepository {

    /**
     * Create a data source that returns pages of results keyed by page.
     */
    fun getPopularMovies(): PagingSource<Int, EntityMovieItem>

    /**
     * Add Favorite movie
     */
    suspend fun addFavoriteMovie(movie: EntityFavoriteMovieItem): Flow<Boolean>

    /**
     * Remove Favorite movie
     */
    suspend fun removeFavoriteMovie(movie: EntityFavoriteMovieItem): Flow<Boolean>

    /**
     * Create a data source that returns pages of results keyed by page.
     */
    fun getFavoriteMovies(): Flow<List<EntityFavoriteMovieItem>>
}