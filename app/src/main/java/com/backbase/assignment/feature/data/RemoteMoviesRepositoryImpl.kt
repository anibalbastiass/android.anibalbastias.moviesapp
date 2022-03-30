package com.backbase.assignment.feature.data

import com.backbase.assignment.feature.data.model.list.MovieData
import com.backbase.assignment.feature.data.state.APIState
import com.backbase.assignment.feature.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

typealias MovieDataState = APIState<MovieData>

class RemoteMoviesRepositoryImpl @Inject constructor(
    private val service: RemoteMoviesService
) : RemoteMoviesRepository {

    override suspend fun getNowPlaying(): Flow<MovieDataState> =
        flow {
            val response = service.getMoviesByType()

            if (response.isSuccessful) {
                response.body()?.let { movies ->
                    emit(APIState.Success(movies))
                } ?: emit(APIState.Empty(response.message()))
            } else {
                emit(APIState.Error(response.message()))
            }
        }
}
