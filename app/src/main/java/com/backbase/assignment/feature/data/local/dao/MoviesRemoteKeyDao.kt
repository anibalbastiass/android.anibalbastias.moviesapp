package com.backbase.assignment.feature.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.backbase.assignment.feature.data.local.model.DBConstants
import com.backbase.assignment.feature.data.local.model.EntityMovieKey

@Dao
interface MoviesRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(userRemoteKeyEntity: List<EntityMovieKey>)

    @Query("SELECT * FROM ${DBConstants.MOVIE_REMOTE_KEY_TABLE} WHERE id = :movieId")
    fun getRemoteKey(movieId: String): EntityMovieKey

    @Query("DELETE FROM ${DBConstants.MOVIE_REMOTE_KEY_TABLE}")
    fun clearAll()
}