package com.anibalbastias.moviesapp.feature.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anibalbastias.moviesapp.feature.data.remote.RemoteMoviesService
import com.anibalbastias.moviesapp.feature.data.remote.mapper.RemoteMovieItemMapper
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieItem
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class SearchMoviesPagingSource @Inject constructor(
    private val service: RemoteMoviesService,
    private val mapper: RemoteMovieItemMapper,
) : PagingSource<Int, DomainMovieItem>() {

    companion object {
        const val POST_STARTING_PAGE_INDEX = 1
    }

    var query: String = ""

    override fun getRefreshKey(state: PagingState<Int, DomainMovieItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainMovieItem> {
        return try {
            val position = params.key ?: POST_STARTING_PAGE_INDEX
            val response = service.searchPagedMovies(query = query, page = position)
            val prevKey = if (position == POST_STARTING_PAGE_INDEX) null else position - 1
            val nextKey = if (response.results!!.isEmpty()) null else position + 1
            LoadResult.Page(
                data = with(mapper) { response.results.map { it.fromRemoteToDomain() } },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}