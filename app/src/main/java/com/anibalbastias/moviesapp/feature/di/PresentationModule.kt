package com.anibalbastias.moviesapp.feature.di

import androidx.paging.ExperimentalPagingApi
import com.anibalbastias.moviesapp.feature.data.local.LocalMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.domain.paging.MoviesPagingMediator
import com.anibalbastias.moviesapp.feature.domain.paging.SearchMoviesPagingSource
import com.anibalbastias.moviesapp.feature.domain.usecase.local.*
import com.anibalbastias.moviesapp.feature.domain.usecase.remote.*
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
    fun provideSearchPagingMoviesUseCase(
        pagingSource: SearchMoviesPagingSource,
        mapper: UiMovieMapper,
    ): SearchPagingMoviesUseCase {
        return SearchPagingMoviesUseCase(pagingSource, mapper)
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

    @Provides
    fun provideClearAllSavedMoviesUseCase(
        local: LocalMoviesRepositoryImpl,
    ): ClearAllSavedMoviesUseCase {
        return ClearAllSavedMoviesUseCase(local)
    }

    @Provides
    fun provideGetSavedMoviesUseCase(
        local: LocalMoviesRepositoryImpl,
        mapper: UiMovieMapper,
    ): GetSavedMoviesUseCase {
        return GetSavedMoviesUseCase(local, mapper)
    }

    @Provides
    fun provideSaveMovieUseCase(
        local: LocalMoviesRepositoryImpl,
        mapper: UiMovieMapper,
    ): AddSavedMovieUseCase {
        return AddSavedMovieUseCase(local, mapper)
    }

    @Provides
    fun provideRemoveSavedMovieByIdUseCase(
        local: LocalMoviesRepositoryImpl,
    ): RemoveSavedMovieByTitleUseCase {
        return RemoveSavedMovieByTitleUseCase(local)
    }

    @Provides
    fun provideGetMovieVideosUseCase(
        remote: RemoteMoviesRepositoryImpl,
    ): GetMovieVideosUseCase {
        return GetMovieVideosUseCase(remote)
    }

    @Provides
    fun provideGetMovieCreditsById(
        remote: RemoteMoviesRepositoryImpl,
    ): GetMovieCreditsById {
        return GetMovieCreditsById(remote)
    }

    @Provides
    fun provideGetMovieProvidersById(
        remote: RemoteMoviesRepositoryImpl,
    ): GetMovieProvidersById {
        return GetMovieProvidersById(remote)
    }

    @Provides
    fun provideGetMovieSimilarById(
        remote: RemoteMoviesRepositoryImpl,
    ): GetMovieSimilarById {
        return GetMovieSimilarById(remote)
    }

    @Provides
    fun provideGetMovieTranslationsById(
        remote: RemoteMoviesRepositoryImpl,
    ): GetMovieTranslationsById {
        return GetMovieTranslationsById(remote)
    }
}