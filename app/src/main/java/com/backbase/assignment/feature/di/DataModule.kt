package com.backbase.assignment.feature.di

import android.content.Context
import com.backbase.assignment.BuildConfig
import com.backbase.assignment.MoviesApplication
import com.backbase.assignment.feature.data.RemoteMoviesService
import com.backbase.assignment.feature.data.interceptor.ConnectionInterceptor
import com.backbase.assignment.feature.data.interceptor.SecurityInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MoviesApplication {
        return app as MoviesApplication
    }

    @Provides
    @Singleton
    fun provideContext(application: MoviesApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideMoshiBuilder(): MoshiConverterFactory {
        return MoshiConverterFactory.create(Moshi.Builder().build())
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: MoshiConverterFactory, context: Context): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(moshi)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .addInterceptor(SecurityInterceptor())
                    .addInterceptor(ConnectionInterceptor(context))
                    .build()
            )
    }

    @Singleton
    @Provides
    fun provideMoviesService(retrofit: Retrofit.Builder): RemoteMoviesService {
        return retrofit
            .build()
            .create(RemoteMoviesService::class.java)
    }
}