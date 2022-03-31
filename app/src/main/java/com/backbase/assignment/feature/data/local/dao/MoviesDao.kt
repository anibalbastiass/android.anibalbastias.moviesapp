package com.backbase.assignment.feature.data.local.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.backbase.assignment.feature.data.local.model.DBConstants
import com.backbase.assignment.feature.data.local.model.EntityMovieItem

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<EntityMovieItem>)

    @Query("SELECT * FROM ${DBConstants.MOVIES_TABLE}")
    fun getMovies(): DataSource.Factory<Int, EntityMovieItem>

    @Query("DELETE FROM ${DBConstants.MOVIES_TABLE}")
    fun clearMovies()
}