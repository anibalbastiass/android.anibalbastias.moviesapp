package com.backbase.assignment.feature.domain

import com.backbase.assignment.feature.data.remote.RemoteMoviesRepositoryImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: RemoteMoviesRepositoryImpl
) {
    suspend fun execute(): Flow<DomainMovieDataState> = repository.getNowPlaying()
}