package com.backbase.assignment.feature.di

import androidx.paging.PagedList
import com.backbase.assignment.feature.data.remote.RemoteMoviesRepositoryImpl
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.FIRST_PAGE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.PAGE_SIZE
import com.backbase.assignment.feature.data.remote.paging.PageLoadingMoviesCallback
import com.backbase.assignment.feature.domain.GetNowPlayingMoviesUseCase
import com.backbase.assignment.feature.domain.GetPagedPopularMoviesUseCase
import com.backbase.assignment.feature.domain.GetPopularMoviesUseCase
import com.backbase.assignment.feature.presentation.mapper.UiMovieItemMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    fun provideUiMovieMapper(): UiMovieItemMapper = UiMovieItemMapper()

    @Provides
    fun provideGetNowPlayingMoviesUseCase(
        remote: RemoteMoviesRepositoryImpl
    ): GetNowPlayingMoviesUseCase {
        return GetNowPlayingMoviesUseCase(remote)
    }

    @Provides
    fun provideGetPopularMoviesUseCase(
        remote: RemoteMoviesRepositoryImpl
    ): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(remote)
    }

    @Provides
    fun provideGetPagedPopularMoviesUseCase(
        remote: RemoteMoviesRepositoryImpl
    ): GetPagedPopularMoviesUseCase {
        return GetPagedPopularMoviesUseCase(remote)
    }

    @Provides
    fun providePagedCoroutineContext(): CoroutineContext {
        return SupervisorJob() + Dispatchers.Main
    }

    @Provides
    fun providePageLoadingBoundaryCallback(
        coroutineContext: CoroutineContext,
        useCase: GetPopularMoviesUseCase
    ): PageLoadingMoviesCallback {
        return PageLoadingMoviesCallback(coroutineContext, useCase, FIRST_PAGE, PAGE_SIZE)
    }

    @Provides
    fun providePagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setMaxSize(224)
            .setPrefetchDistance(PAGE_SIZE * 2)
            .build()
    }
}