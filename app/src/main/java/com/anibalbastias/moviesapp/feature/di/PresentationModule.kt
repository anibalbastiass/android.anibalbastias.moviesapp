package com.anibalbastias.moviesapp.feature.di

import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.domain.mediator.MoviesPagingMediator
import com.anibalbastias.moviesapp.feature.domain.usecase.GetMovieDetailUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.GetNowPlayingMoviesUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.GetPagingMoviesUseCase
import com.anibalbastias.moviesapp.feature.presentation.mapper.UiMovieMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@ExperimentalPagingApi
@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    fun provideUiMovieMapper(): UiMovieMapper = UiMovieMapper()

    @Provides
    fun provideGetNowPlayingMoviesUseCase(
        remote: RemoteMoviesRepositoryImpl,
    ): GetNowPlayingMoviesUseCase {
        return GetNowPlayingMoviesUseCase(remote)
    }

    @Provides
    fun provideGetPagedPopularMoviesUseCase(
        remote: RemoteMoviesRepositoryImpl,
        mapper: UiMovieMapper,
        mediator: MoviesPagingMediator,
    ): GetPagingMoviesUseCase {
        return GetPagingMoviesUseCase(remote, mapper, mediator)
    }

    @Provides
    fun provideGetMovieDetailUseCase(
        remote: RemoteMoviesRepositoryImpl,
    ): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(remote)
    }

    @Provides
    fun providePagedCoroutineContext(): CoroutineContext {
        return SupervisorJob() + Dispatchers.Main
    }
}