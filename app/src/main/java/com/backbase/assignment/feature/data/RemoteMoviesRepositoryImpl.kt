package com.backbase.assignment.feature.data

import com.backbase.assignment.feature.data.model.list.MovieData
import com.backbase.assignment.feature.data.state.APIState
import com.backbase.assignment.feature.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

typealias MovieDataState = APIState<MovieData>

class RemoteMoviesRepositoryImpl @Inject constructor(
    private val service: RemoteMoviesService
) : RemoteMoviesRepository {

    override suspend fun getNowPlaying(): Flow<MovieDataState> =
        flow { emit(service.getMoviesByType()) }
            .onStart { APIState.Loading }
            .map { moviesBody ->
                if (moviesBody.isSuccessful) {
                    moviesBody.body()?.let { movies ->
                        APIState.Success(movies)
                    } ?: APIState.Empty(moviesBody.message())
                } else {
                    APIState.Error(moviesBody.message())
                }
            }
            .flowOn(Dispatchers.IO)
}
