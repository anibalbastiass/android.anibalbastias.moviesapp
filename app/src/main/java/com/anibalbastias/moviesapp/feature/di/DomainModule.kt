package com.anibalbastias.moviesapp.feature.di

import com.anibalbastias.moviesapp.feature.data.local.LocalMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.data.local.dao.FavoritesDao
import com.anibalbastias.moviesapp.feature.data.local.dao.MoviesDao
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesService
import com.anibalbastias.moviesapp.feature.data.remote.mapper.RemoteMovieItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideRemoteMoviesRepository(
        service: RemoteMoviesService,
        mapper: RemoteMovieItemMapper,
    ): RemoteMoviesRepositoryImpl {
        return RemoteMoviesRepositoryImpl(service, mapper)
    }

    @Singleton
    @Provides
    fun provideLocalMoviesRepository(
        movieDao: MoviesDao,
        favoriteDao: FavoritesDao,
    ): LocalMoviesRepositoryImpl {
        return LocalMoviesRepositoryImpl(movieDao, favoriteDao)
    }
}