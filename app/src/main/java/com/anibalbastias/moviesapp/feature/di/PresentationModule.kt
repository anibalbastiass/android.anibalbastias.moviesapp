package com.anibalbastias.moviesapp.feature.di

import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.moviesapp.feature.data.local.LocalMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.domain.mediator.MoviesPagingMediator
import com.anibalbastias.moviesapp.feature.domain.usecase.local.AddFavoriteMovieUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.local.GetFavoriteMoviesUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.local.RemoveFavoriteMovieUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.remote.GetMovieDetailUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.remote.GetNowPlayingMoviesUseCase
import com.anibalbastias.moviesapp.feature.domain.usecase.remote.GetPagingMoviesUseCase
import com.anibalbastias.moviesapp.feature.presentation.mapper.UiMovieMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

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
    fun provideGetFavoriteMoviesUseCase(
        local: LocalMoviesRepositoryImpl,
        mapper: UiMovieMapper,
    ): GetFavoriteMoviesUseCase {
        return GetFavoriteMoviesUseCase(local, mapper)
    }

    @Provides
    fun provideGetPagedPopularMoviesUseCase(
        local: LocalMoviesRepositoryImpl,
        mapper: UiMovieMapper,
        mediator: MoviesPagingMediator,
    ): GetPagingMoviesUseCase {
        return GetPagingMoviesUseCase(local, mapper, mediator)
    }

    @Provides
    fun provideAddFavoriteMovieUseCase(
        local: LocalMoviesRepositoryImpl,
        mapper: UiMovieMapper,
    ): AddFavoriteMovieUseCase {
        return AddFavoriteMovieUseCase(local, mapper)
    }

    @Provides
    fun provideRemoveFavoriteMovieUseCase(
        local: LocalMoviesRepositoryImpl,
        mapper: UiMovieMapper,
    ): RemoveFavoriteMovieUseCase {
        return RemoveFavoriteMovieUseCase(local, mapper)
    }

    @Provides
    fun provideGetMovieDetailUseCase(
        remote: RemoteMoviesRepositoryImpl,
    ): GetMovieDetailUseCase {
        return GetMovieDetailUseCase(remote)
    }
}