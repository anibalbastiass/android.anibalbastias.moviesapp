package com.backbase.assignment.feature.di

import android.app.Application
import androidx.paging.PagedList
import com.backbase.assignment.feature.data.local.MoviesDatabase
import com.backbase.assignment.feature.data.local.dao.MoviesDao
import com.backbase.assignment.feature.data.remote.paging.PageLoadingMoviesCallback
import com.backbase.assignment.feature.domain.repository.RemoteMoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

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