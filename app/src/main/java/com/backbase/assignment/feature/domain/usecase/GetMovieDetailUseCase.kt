package com.backbase.assignment.feature.domain.usecase

import com.backbase.assignment.feature.data.remote.RemoteMoviesRepositoryImpl
import com.backbase.assignment.feature.domain.DomainMovieDetailDataState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetMovieDetailUseCase @Inject constructor(
    private val repository: RemoteMoviesRepositoryImpl,
) {
    suspend fun execute(movieId: String): Flow<DomainMovieDetailDataState> =
        repository.getMovieById(movieId)
}