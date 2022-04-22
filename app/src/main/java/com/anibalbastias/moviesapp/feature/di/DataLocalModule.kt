package com.anibalbastias.moviesapp.feature.di

import android.app.Application
import com.anibalbastias.moviesapp.feature.data.local.MoviesDatabase
import com.anibalbastias.moviesapp.feature.data.local.dao.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataLocalModule {

    @Singleton
    @Provides
    fun provideAlbumDatabase(application: Application): MoviesDatabase {
        return MoviesDatabase.getInstance(application)
            ?: throw RuntimeException("Database is not ready")
    }

    @Singleton
    @Provides
    fun provideAlbumDao(database: MoviesDatabase): MoviesDao {
        return database.moviesDao()
    }
}