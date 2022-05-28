package com.anibalbastias.moviesapp.feature.domain.usecase.remote

import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesRepositoryImpl
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.domain.model.DomainMoviePerson
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetMoviePersonUseCase @Inject constructor(
    private val repository: RemoteMoviesRepositoryImpl,
) {
    suspend fun execute(personId: String): Flow<APIState<DomainMoviePerson>> =
        repository.getMoviePersonById(personId)
}