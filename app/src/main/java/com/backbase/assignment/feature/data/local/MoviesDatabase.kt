package com.backbase.assignment.feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.backbase.assignment.feature.data.local.dao.MoviesDao
import com.backbase.assignment.feature.data.local.dao.MoviesRemoteKeyDao
import com.backbase.assignment.feature.data.local.model.DBConstants
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.data.local.model.EntityMovieKey

@Database(
    entities = [EntityMovieItem::class, EntityMovieKey::class],
    version = DBConstants.DATABASE_VERSION_CODE,
    exportSchema = true
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun moviesKeysDao(): MoviesRemoteKeyDao
}
