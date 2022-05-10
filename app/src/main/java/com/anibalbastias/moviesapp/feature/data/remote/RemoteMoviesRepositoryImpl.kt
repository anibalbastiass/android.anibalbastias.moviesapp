package com.anibalbastias.moviesapp.feature.data.remote

import com.anibalbastias.moviesapp.feature.data.remote.mapper.RemoteMovieItemMapper
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDataState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDetailDataState
import com.anibalbastias.moviesapp.feature.domain.model.*
import com.anibalbastias.moviesapp.feature.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteMoviesRepositoryImpl @Inject constructor(
    private val service: RemoteMoviesService,
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

    override suspend fun getMovieVideosById(movieId: String): Flow<List<DomainMovieVideoItem>> =
        flow {
            val response = service.getMovieVideosById(movieId)

            with(mapper) {
                emit(
                    response.results!!.map { it.fromRemoteToDomain() }
                )
            }
        }

    override suspend fun getMovieCreditsById(movieId: String): Flow<DomainMovieCredits> =
        flow {
            val response = service.getMovieCreditsById(movieId)
            with(mapper) { emit(response.fromRemoteToDomain()) }
        }

    override suspend fun getMovieProvidersById(movieId: String): Flow<List<DomainMovieProviderItem>> =
        flow {
            val response = service.getMovieProvidersById(movieId)
            with(mapper) {
                emit(
                    response.results?.map {
                        it.value.fromRemoteToDomain(it.key)
                    } ?: listOf()
                )
            }
        }

    override suspend fun getMovieSimilarById(movieId: String): Flow<List<DomainMovieItem>> =
        flow {
            val response = service.getMovieSimilarById(movieId)
            emit(
                response.results?.map {
                    with(mapper) { it.fromRemoteToDomain() }
                } ?: listOf()
            )
        }

    override suspend fun getMovieTranslationsById(movieId: String): Flow<List<DomainMovieTranslationItem>> =
        flow {
            val response = service.getMovieTranslationsById(movieId)
            with(mapper) {
                emit(
                    response.translations?.map {
                        it.fromRemoteToDomain()
                    } ?: listOf()
                )
            }
        }
}
