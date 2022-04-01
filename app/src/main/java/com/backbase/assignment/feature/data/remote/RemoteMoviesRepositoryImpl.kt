package com.backbase.assignment.feature.data.remote

import androidx.paging.DataSource
import com.backbase.assignment.feature.data.local.dao.MoviesDao
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.data.remote.mapper.RemoteMovieItemMapper
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.DomainMovieDataState
import com.backbase.assignment.feature.domain.DomainMovieDetailDataState
import com.backbase.assignment.feature.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteMoviesRepositoryImpl @Inject constructor(
    private val service: RemoteMoviesService,
    private val dao: MoviesDao,
    private val mapper: RemoteMovieItemMapper,
) : RemoteMoviesRepository {

    override suspend fun getNowPlaying(): Flow<DomainMovieDataState> =
        flow {
            val response = service.getMoviesByType()

            if (response.isSuccessful) {
                response.body()?.let { movies ->
                    with(mapper) {
                        checkNotNull(movies.results) {
                            throw NullPointerException("No movies available")
                        }

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

    override suspend fun loadPageOfMovies(pageToLoad: Int, pageSize: Int): List<EntityMovieItem> {
        val movies = service.getMoviesByType(page = pageToLoad.toString())
        val newPage = mapper.fromRemoteToEntity(movies.body()!!)
        dao.insert(newPage)
        return newPage
    }

    override fun getPopularMovies(): DataSource.Factory<Int, EntityMovieItem> {
        return dao.getMovies()
    }

    override suspend fun getMovieById(movieId: String): Flow<DomainMovieDetailDataState> =
        flow {
            val response = service.getMovieById(movieId)

            if (response.isSuccessful) {
                response.body()?.let { movies ->
                    with(mapper) {
                        emit(
                            APIState.Success(movies.fromRemoteToDomain())
                        )
                    }
                } ?: emit(APIState.Empty(response.message()))
            } else {
                emit(APIState.Error(response.message()))
            }
        }
}
