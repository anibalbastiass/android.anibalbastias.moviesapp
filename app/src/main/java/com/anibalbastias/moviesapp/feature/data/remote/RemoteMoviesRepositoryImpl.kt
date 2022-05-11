package com.anibalbastias.moviesapp.feature.data.remote

import com.anibalbastias.moviesapp.feature.data.remote.mapper.RemoteMovieItemMapper
import com.anibalbastias.moviesapp.feature.data.remote.model.*
import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDataState
import com.anibalbastias.moviesapp.feature.domain.DomainMovieDetailDataState
import com.anibalbastias.moviesapp.feature.domain.model.*
import com.anibalbastias.moviesapp.feature.domain.repository.RemoteMoviesRepository
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
        combine(
            flowOf(service.getMovieById(movieId)),
            flowOf(service.getMovieVideosById(movieId)),
            flowOf(service.getMovieCreditsById(movieId)),
            flowOf(service.getMovieProvidersById(movieId)),
            flowOf(service.getMovieSimilarById(movieId)),
            flowOf(service.getMovieTranslationsById(movieId)),
        ) { flowArray ->
            val detail: DomainMovieDetail =
                with(mapper) {
                    (flowArray[0] as RemoteMovieDetail).fromRemoteToDomain(
                        videos = flowArray[1] as RemoteMovieVideos,
                        credits = flowArray[2] as RemoteMovieCredits,
                        providers = flowArray[3] as RemoteMovieProviders,
                        similar = flowArray[4] as RemoteMovieData,
                        translations = flowArray[5] as RemoteMovieTranslations,
                    )
                }
            APIState.Success(detail)
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
