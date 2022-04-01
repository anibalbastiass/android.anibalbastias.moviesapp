package com.backbase.assignment.feature.data.remote.paging

import android.util.Log
import androidx.paging.PagedList
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

/**
 * Abstract common BoundaryCallback which loads a page of Movies
 */
class PageLoadingMoviesCallback(
    override val coroutineContext: CoroutineContext,
    private val useCase: GetPopularMoviesUseCase,
    private val pageSize: Int,
    firstPageNumber: Int = 1
) : PagedList.BoundaryCallback<EntityMovieItem>(), CoroutineScope {

    companion object {
        const val TAG = "PageLoadingBoundary"
    }

    private var currentPage = firstPageNumber

    override fun onZeroItemsLoaded() {
        tryLoadPage(currentPage)
    }

    override fun onItemAtEndLoaded(itemAtEnd: EntityMovieItem) {
        tryLoadPage(++currentPage)
    }

    private fun tryLoadPage(pageToLoad: Int) {
        launch {
            try {
                Log.v(TAG, "pageToLoad: $pageToLoad, pageSize: $pageSize")
                withContext(Dispatchers.IO) {
                    useCase.execute(pageToLoad, pageSize)
                }
            } catch (e: Exception) {
                Log.e(TAG, "error occurred loading page", e)
            }
        }
    }
}