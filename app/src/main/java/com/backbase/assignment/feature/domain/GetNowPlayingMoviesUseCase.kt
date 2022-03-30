package com.backbase.assignment.feature.domain

import com.backbase.assignment.feature.data.MovieDataState
import com.backbase.assignment.feature.data.RemoteMoviesRepositoryImpl
import com.backbase.assignment.feature.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: RemoteMoviesRepositoryImpl
) {
    suspend fun execute(): Flow<MovieDataState> = repository.getNowPlaying()
}