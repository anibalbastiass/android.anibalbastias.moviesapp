package com.anibalbastias.moviesapp.feature.domain.repository

import com.anibalbastias.moviesapp.feature.domain.DomainMovieDataState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDetailDataState
import kotlinx.coroutines.flow.Flow

interface RemoteMoviesRepository {

    /**
     * Load simple list of Movies by Now Playing label
     */
    suspend fun getNowPlaying(): Flow<DomainMovieDataState>

    /**
     * Load Movie detail data
     * @param movieId Id of movie
     */
    suspend fun getMovieById(movieId: String): Flow<DomainMovieDetailDataState>
}