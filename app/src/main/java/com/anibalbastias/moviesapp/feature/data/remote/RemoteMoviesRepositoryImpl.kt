package com.anibalbastias.moviesapp.feature.data.remote

import androidx.paging.DataSource
import com.anibalbastias.moviesapp.feature.data.local.dao.MoviesDao
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.data.remote.mapper.RemoteMovieItemMapper
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.POPULAR
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDataState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDetailDataState
import com.anibalbastias.moviesapp.feature.domain.repository.RemoteMoviesRepository
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
            val response = service.getMoviesByType(movieType = "now_playing")

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
        val movies = service.getMoviesByType(movieType = POPULAR, page = pageToLoad.toString())
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
