package com.backbase.assignment.feature.di

import androidx.paging.PagedList
import com.backbase.assignment.feature.data.remote.RemoteMoviesRepositoryImpl
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
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    private const val firstPage = 1
    private const val pageSize = 20

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
        return PageLoadingMoviesCallback(coroutineContext, useCase, firstPage, pageSize)
    }

    @Provides
    fun providePagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setMaxSize(224)
            .setPrefetchDistance(pageSize * 2)
            .build()
    }
}