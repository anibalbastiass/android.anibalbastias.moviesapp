package com.anibalbastias.moviesapp.feature.domain.repository

import com.anibalbastias.moviesapp.feature.domain.DomainMovieDataState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDetailDataState
import com.anibalbastias.moviesapp.feature.domain.model.*
import kotlinx.coroutines.flow.Flow

interface RemoteMoviesRepository {

    suspend fun getNowPlaying(): Flow<DomainMovieDataState>

    suspend fun getMovieById(movieId: String): Flow<DomainMovieDetailDataState>

    suspend fun getMovieVideosById(movieId: String): Flow<List<DomainMovieVideoItem>>

    suspend fun getMovieCreditsById(movieId: String): Flow<DomainMovieCredits>

    suspend fun getMovieProvidersById(movieId: String): Flow<List<DomainMovieProviderItem>>

    suspend fun getMovieSimilarById(movieId: String): Flow<List<DomainMovieItem>>

    suspend fun getMovieTranslationsById(movieId: String): Flow<List<DomainMovieTranslationItem>>
}