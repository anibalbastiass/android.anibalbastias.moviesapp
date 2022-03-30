package com.backbase.assignment.feature.di

import com.backbase.assignment.feature.data.RemoteMoviesRepositoryImpl
import com.backbase.assignment.feature.data.RemoteMoviesService
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
        service: RemoteMoviesService
    ): RemoteMoviesRepositoryImpl {
        return RemoteMoviesRepositoryImpl(service)
    }
}