package com.backbase.assignment.feature.data.remote

import androidx.paging.DataSource
import com.backbase.assignment.feature.data.local.dao.MoviesDao
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.data.remote.mapper.RemoteMovieItemMapper
import com.backbase.assignment.feature.data.remote.model.RemoteMovieData
import com.backbase.assignment.feature.data.remote.model.RemoteMovieResult
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.DomainMovieDataState
import com.backbase.assignment.feature.domain.RemoteMovieDataState
import com.backbase.assignment.feature.domain.repository.RemoteMoviesRepository
import com.backbase.assignment.feature.presentation.model.UiMovieItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
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
        if (pageToLoad == 1) {
            dao.clearMovies()
        }

        delay(500)

        val movies = service.getMoviesByType(page = pageToLoad.toString())
        val newPage = mapper.fromRemoteToEntity(movies.body()!!)
        dao.insert(newPage)
        return newPage
    }

    override fun getPopularMovies(): DataSource.Factory<Int, EntityMovieItem> {
        return dao.getMovies()
    }
}
