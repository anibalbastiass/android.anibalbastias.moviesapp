package com.backbase.assignment.feature.domain.repository

import com.backbase.assignment.feature.data.MovieDataState
import kotlinx.coroutines.flow.Flow

interface RemoteMoviesRepository {
//    suspend fun getPopular(): Flow<APIState<MovieData>>
    suspend fun getNowPlaying(): Flow<MovieDataState>
}