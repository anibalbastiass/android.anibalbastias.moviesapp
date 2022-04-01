package com.backbase.assignment.feature.domain.usecase

import com.backbase.assignment.feature.data.remote.RemoteMoviesRepositoryImpl
import com.backbase.assignment.feature.domain.DomainMovieDataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: RemoteMoviesRepositoryImpl
) {
    suspend fun execute(): Flow<DomainMovieDataState> = repository.getNowPlaying()
}