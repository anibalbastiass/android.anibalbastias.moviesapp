package com.anibalbastias.moviesapp.feature.domain.usecase.remote

import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieVideoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetMovieVideosUseCase @Inject constructor(
    private val repository: RemoteMoviesRepositoryImpl,
) {
    suspend fun execute(movieId: String): Flow<List<DomainMovieVideoItem>> =
        repository.getMovieVideosById(movieId)
}