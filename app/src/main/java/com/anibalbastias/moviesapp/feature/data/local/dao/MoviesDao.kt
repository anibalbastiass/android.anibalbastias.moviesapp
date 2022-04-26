package com.anibalbastias.moviesapp.feature.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.anibalbastias.moviesapp.feature.data.local.model.DBConstants
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.IS_FAVORITE

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<EntityMovieItem>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateMovie(movie: EntityMovieItem)

    @Query("SELECT * FROM ${DBConstants.MOVIES_TABLE}")
    fun getAllMovies(): PagingSource<Int, EntityMovieItem>

    @Query("SELECT * FROM ${DBConstants.MOVIES_TABLE} WHERE $IS_FAVORITE = :isFavorite")
    fun getFavoriteMovies(isFavorite: Boolean = true): PagingSource<Int, EntityMovieItem>

    @Query("DELETE FROM ${DBConstants.MOVIES_TABLE}")
    fun clearAll()
}