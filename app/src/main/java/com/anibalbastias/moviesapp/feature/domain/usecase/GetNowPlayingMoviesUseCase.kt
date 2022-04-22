package com.anibalbastias.moviesapp.feature.domain.usecase

import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: RemoteMoviesRepositoryImpl
) {
    suspend fun execute(): Flow<DomainMovieDataState> = repository.getNowPlaying()
}