package com.anibalbastias.moviesapp.feature.data.local.dao

import androidx.room.*
import com.anibalbastias.moviesapp.feature.data.local.model.DBConstants
import com.anibalbastias.moviesapp.feature.data.local.model.EntityFavoriteMovieItem

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(movie: EntityFavoriteMovieItem)

    @Query("SELECT * FROM ${DBConstants.FAVORITE_MOVIES_TABLE}")
    suspend fun getFavoriteList(): List<EntityFavoriteMovieItem>

    @Delete
    suspend fun removeFavoriteMovies(movie: EntityFavoriteMovieItem)
}