package com.anibalbastias.moviesapp.feature.di

import android.content.Context
import com.anibalbastias.moviesapp.BuildConfig
import com.anibalbastias.moviesapp.MoviesApplication
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesService
import com.anibalbastias.moviesapp.feature.data.remote.interceptor.ConnectionInterceptor
import com.anibalbastias.moviesapp.feature.data.remote.interceptor.SecurityInterceptor
import com.anibalbastias.moviesapp.feature.data.remote.mapper.RemoteMovieItemMapper
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DataRemoteModule {

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
    fun provideCertificatePinner(): CertificatePinner {
        return CertificatePinner.Builder()
            .add(BuildConfig.API_HOST, BuildConfig.API_PUBLIC_KEY_HASH)
            .build()
    }

    @Singleton
    @Provides
    fun provideTLSConnection(): ConnectionSpec {
        return ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(
        context: Context,
        certificatePinner: CertificatePinner,
        connectionSpec: ConnectionSpec,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
            .connectionSpecs(Collections.singletonList(connectionSpec))
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .addInterceptor(SecurityInterceptor())
            .addInterceptor(ConnectionInterceptor(context))
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        moshi: MoshiConverterFactory,
        okHttpClient: OkHttpClient,
        context: Context,
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(moshi)
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun provideMoviesService(retrofit: Retrofit.Builder): RemoteMoviesService {
        return retrofit
            .build()
            .create(RemoteMoviesService::class.java)
    }

    @Singleton
    @Provides
    fun provideRemoteMovieItemMapper(): RemoteMovieItemMapper {
        return RemoteMovieItemMapper()
    }
}