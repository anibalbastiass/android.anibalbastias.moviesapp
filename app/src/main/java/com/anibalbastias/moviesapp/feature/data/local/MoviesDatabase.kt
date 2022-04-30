package com.anibalbastias.moviesapp.feature.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anibalbastias.moviesapp.feature.data.local.dao.FavoritesDao
import com.anibalbastias.moviesapp.feature.data.local.dao.MoviesDao
import com.anibalbastias.moviesapp.feature.data.local.dao.MoviesRemoteKeyDao
import com.anibalbastias.moviesapp.feature.data.local.dao.SavedMoviesDao
import com.anibalbastias.moviesapp.feature.data.local.model.*

@Database(
    entities = [
        EntityMovieItem::class,
        EntityFavoriteMovieItem::class,
        EntityMovieKey::class,
        EntitySavedMovieItem::class
    ],
    version = DBConstants.DATABASE_VERSION_CODE,
    exportSchema = true
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun favoriteMoviesDao(): FavoritesDao
    abstract fun savedMoviesDao(): SavedMoviesDao
    abstract fun moviesKeysDao(): MoviesRemoteKeyDao
}
