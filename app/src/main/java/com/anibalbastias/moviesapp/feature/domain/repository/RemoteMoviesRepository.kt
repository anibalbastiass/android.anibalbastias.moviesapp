package com.anibalbastias.moviesapp.feature.domain.repository

import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDataState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDetailDataState
import com.anibalbastias.moviesapp.feature.domain.model.DomainMoviePerson
import kotlinx.coroutines.flow.Flow

interface RemoteMoviesRepository {

    suspend fun getNowPlaying(): Flow<DomainMovieDataState>

    suspend fun getMovieById(movieId: String): Flow<DomainMovieDetailDataState>

    suspend fun getMoviePersonById(personId: String): Flow<APIState<DomainMoviePerson>>
}