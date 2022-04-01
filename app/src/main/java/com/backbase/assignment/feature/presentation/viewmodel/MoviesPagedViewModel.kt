package com.backbase.assignment.feature.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.FIRST_PAGE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.PAGE_SIZE
import com.backbase.assignment.feature.data.remote.paging.PageLoadingMoviesCallback
import com.backbase.assignment.feature.domain.usecase.GetPagedPopularMoviesUseCase
import com.backbase.assignment.feature.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesPagedViewModel @Inject constructor(
    pagedCallback: PageLoadingMoviesCallback,
    pagedConfig: PagedList.Config,
    pagedUseCase: GetPagedPopularMoviesUseCase,
    private val popularUseCase: GetPopularMoviesUseCase,
) : ViewModel() {

    private var moviesData: LiveData<PagedList<EntityMovieItem>> =
        LivePagedListBuilder(pagedUseCase.execute(), pagedConfig)
            .setBoundaryCallback(pagedCallback)
            .build()
    var movies: MediatorLiveData<PagedList<EntityMovieItem>> = MediatorLiveData()

    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    init {
        createDataSource()
    }

    private fun createDataSource() {
        movies.addSource(moviesData) { newData ->
            movies.value = newData
        }
    }

    fun refreshMovies() {
        movies.removeSource(moviesData)

        ioScope.launch {
            popularUseCase.execute(FIRST_PAGE, PAGE_SIZE)
        }
    }
}
