package com.anibalbastias.moviesapp.feature.data.local.dao

import androidx.room.*
import com.anibalbastias.moviesapp.feature.data.local.model.DBConstants
import com.anibalbastias.moviesapp.feature.data.local.model.EntityFavoriteMovieItem
import com.anibalbastias.moviesapp.feature.data.local.model.EntitySavedMovieItem
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants

@Dao
interface SavedMoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSavedMovieSearch(movie: EntitySavedMovieItem)

    @Query("SELECT * FROM ${DBConstants.SAVED_MOVIES_TABLE} " +
            "ORDER BY ${RemoteConstants.RELEASE_DATE} DESC LIMIT 10")
    suspend fun getSavedMoviesList(): List<EntitySavedMovieItem>

    @Query("DELETE FROM ${DBConstants.SAVED_MOVIES_TABLE}")
    suspend fun clearAll()

    @Query("DELETE FROM ${DBConstants.SAVED_MOVIES_TABLE} WHERE id = :id")
    suspend fun deleteSavedMovieById(id: String)
}