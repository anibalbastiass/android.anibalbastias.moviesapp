package com.anibalbastias.moviesapp.feature.domain.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.anibalbastias.moviesapp.feature.data.local.MoviesDatabase
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieKey
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesService
import com.anibalbastias.moviesapp.feature.data.remote.mapper.RemoteMovieItemMapper
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.FIRST_PAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class MoviesPagingMediator @Inject constructor(
    private val database: MoviesDatabase,
    private val service: RemoteMoviesService,
    private val mapper: RemoteMovieItemMapper,
) : RemoteMediator<Int, EntityMovieItem>() {

    private val ioScope = CoroutineScope(Dispatchers.IO)

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EntityMovieItem>,
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = ioScope.async {
                    getRemoteKeyClosestToCurrentPosition(state)
                }
                val keys = remoteKeys.await()
                keys?.nextPage?.minus(1) ?: FIRST_PAGE
            }
            LoadType.PREPEND -> {
                val remoteKeys = ioScope.async {
                    getRemoteKeyForFirstItem(state)
                }

                val keys = remoteKeys.await()
                val prevKey = keys?.previousPage

                if (prevKey == null) {
                    return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                }
                prevKey
            }
            LoadType.APPEND -> {
                val remoteKeys = ioScope.async {
                    getRemoteKeyForLastItem(state)
                }

                val keys = remoteKeys.await()
                val nextKey = keys?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)

                nextKey
            }
        }

        try {
            val apiResponse = withContext(ioScope.coroutineContext) {
                service.getPagedMovies(page)
            }

            val movies = apiResponse.results!!
            val endOfPaginationReached = movies.isEmpty()

            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.moviesKeysDao().clearAll()
                    database.moviesDao().clearAll()
                }

                val prevKey = if (page == FIRST_PAGE) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = movies.map {
                    EntityMovieKey(id = it.id.toString(),
                        previousPage = prevKey,
                        nextPage = nextKey)
                }
                database.moviesKeysDao().insertAll(keys)
                database.moviesDao().insertAll(mapper.fromRemoteToEntity(movies))
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private fun getRemoteKeyForLastItem(state: PagingState<Int, EntityMovieItem>): EntityMovieKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { movie ->
                // Get the remote keys of the last item retrieved
                database.moviesKeysDao().getRemoteKey(movie.id)
            }
    }

    private fun getRemoteKeyForFirstItem(state: PagingState<Int, EntityMovieItem>): EntityMovieKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { movie ->
                database.moviesKeysDao().getRemoteKey(movie.id)
            }
    }

    private fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, EntityMovieItem>,
    ): EntityMovieKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { movieId ->
                database.moviesKeysDao().getRemoteKey(movieId)
            }
        }
    }
}