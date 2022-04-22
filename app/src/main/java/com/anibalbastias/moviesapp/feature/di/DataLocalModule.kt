package com.anibalbastias.moviesapp.feature.di

import android.content.Context
import androidx.room.Room
import com.anibalbastias.moviesapp.feature.data.local.MoviesDatabase
import com.anibalbastias.moviesapp.feature.data.local.dao.MoviesDao
import com.anibalbastias.moviesapp.feature.data.local.model.DBConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataLocalModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MoviesDatabase {
        return Room.databaseBuilder(
            appContext,
            MoviesDatabase::class.java,
            DBConstants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(database: MoviesDatabase): MoviesDao {
        return database.moviesDao()
    }
}