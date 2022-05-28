package com.anibalbastias.moviesapp.feature.data.remote

import com.anibalbastias.moviesapp.feature.data.remote.mapper.RemoteMovieItemMapper
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteMovieCreditPerson
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteMoviePerson
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDataState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDetailDataState
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieDetail
import com.anibalbastias.moviesapp.feature.domain.model.DomainMoviePerson
import com.anibalbastias.moviesapp.feature.domain.repository.RemoteMoviesRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
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
            coroutineScope {
                val movie = async { service.getMovieById(movieId) }
                val videos = async { service.getMovieVideosById(movieId) }
                val credits = async { service.getMovieCreditsById(movieId) }
                val providers = async { service.getMovieProvidersById(movieId) }
                val similar = async { service.getMovieSimilarById(movieId) }
                val translations = async { service.getMovieTranslationsById(movieId) }

                val detail: DomainMovieDetail =
                    with(mapper) {
                        movie.await().fromRemoteToDomain(
                            videos = videos.await(),
                            credits = credits.await(),
                            providers = providers.await(),
                            similar = similar.await(),
                            translations = translations.await(),
                        )
                    }
                emit(APIState.Success(detail))
            }
        }

    override suspend fun getMoviePersonById(personId: String): Flow<APIState<DomainMoviePerson>> =
        combine(
            flowOf(service.getMoviePersonById(personId)),
            flowOf(service.getMovieCreditsPersonById(personId))
        ) { flowArray ->
            val person = (flowArray[0] as RemoteMoviePerson)
            val credits = (flowArray[1] as RemoteMovieCreditPerson)

            APIState.Success(
                with(mapper) {
                    person.fromRemoteToDomain(credits)
                }
            )
        }
}
