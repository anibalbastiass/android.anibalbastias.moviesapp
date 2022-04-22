package com.anibalbastias.moviesapp.feature.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anibalbastias.moviesapp.feature.data.local.model.DBConstants
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<EntityMovieItem>)

    @Query("SELECT * FROM ${DBConstants.MOVIES_TABLE}")
    fun getAllMovies(): PagingSource<Int, EntityMovieItem>

    @Query("DELETE FROM ${DBConstants.MOVIES_TABLE}")
    fun clearAll()
}