package com.backbase.assignment.feature.di

import com.backbase.assignment.feature.data.remote.RemoteMoviesRepositoryImpl
import com.backbase.assignment.feature.domain.GetNowPlayingMoviesUseCase
import com.backbase.assignment.feature.presentation.mapper.UiMovieItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    fun provideUiMovieMapper(): UiMovieItemMapper = UiMovieItemMapper()

    @Provides
    fun provideGetCountriesUseCase(
        remote: RemoteMoviesRepositoryImpl
    ): GetNowPlayingMoviesUseCase {
        return GetNowPlayingMoviesUseCase(remote)
    }
}