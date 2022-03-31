package com.backbase.assignment.feature.data.remote

import androidx.paging.DataSource
import com.backbase.assignment.feature.data.remote.mapper.RemoteMovieItemMapper
import com.backbase.assignment.feature.data.remote.model.RemoteMovieData
import com.backbase.assignment.feature.data.remote.model.RemoteMovieResult
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.DomainMovieDataState
import com.backbase.assignment.feature.domain.RemoteMovieDataState
import com.backbase.assignment.feature.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RemoteMoviesRepositoryImpl @Inject constructor(
    private val service: RemoteMoviesService,
    private val mapper: RemoteMovieItemMapper
) : RemoteMoviesRepository {

    override suspend fun getNowPlaying(): Flow<DomainMovieDataState> =
        flow {
            val response = service.getMoviesByType()

            if (response.isSuccessful) {
                response.body()?.let { movies ->
                    with(mapper) {
                        emit(
                            APIState.Success(movies.results.map {
                                it.fromRemoteToDomain()
                            })
                        )
                    }
                } ?: emit(APIState.Empty(response.message()))
            } else {
                emit(APIState.Error(response.message()))
            }
        }

    override suspend fun loadPageOfMovies(pageToLoad: Int, pageSize: Int): List<RemoteMovieResult> {
        TODO("Not yet implemented")
    }

    override fun createMoviesDataSource(): DataSource.Factory<Int, RemoteMovieResult> {
        TODO("Not yet implemented")
    }
}
