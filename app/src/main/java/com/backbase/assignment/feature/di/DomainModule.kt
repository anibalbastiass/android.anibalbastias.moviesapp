package com.backbase.assignment.feature.di

import com.backbase.assignment.feature.data.remote.RemoteMoviesRepositoryImpl
import com.backbase.assignment.feature.data.remote.RemoteMoviesService
import com.backbase.assignment.feature.data.remote.mapper.RemoteMovieItemMapper
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
        mapper: RemoteMovieItemMapper
    ): RemoteMoviesRepositoryImpl {
        return RemoteMoviesRepositoryImpl(service, mapper)
    }
}