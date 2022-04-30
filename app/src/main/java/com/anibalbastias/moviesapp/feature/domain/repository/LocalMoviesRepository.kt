package com.anibalbastias.moviesapp.feature.domain.repository

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anibalbastias.moviesapp.feature.data.local.model.DBConstants
import com.anibalbastias.moviesapp.feature.data.local.model.EntityFavoriteMovieItem
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.data.local.model.EntitySavedMovieItem
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

    /**
     * Add Saved movie
     */
    suspend fun addSavedMovieSearch(movie: EntitySavedMovieItem): Flow<Boolean>

    /**
     * Fetch all saved movies
     */
    suspend fun getSavedMoviesList(): Flow<List<EntitySavedMovieItem>>

    /**
     * Remove All saved movies
     */
    suspend fun clearAllSavedMovies(): Flow<Boolean>

    /**
     * Remove saved search by title
     * @param title movie search title
     */
    suspend fun deleteSavedMovieByTitle(title: String): Flow<Boolean>
}