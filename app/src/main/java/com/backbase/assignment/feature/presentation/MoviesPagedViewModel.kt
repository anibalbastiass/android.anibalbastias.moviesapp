package com.backbase.assignment.feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.backbase.assignment.feature.data.local.model.EntityMovieItem
import com.backbase.assignment.feature.data.remote.paging.PageLoadingMoviesCallback
import com.backbase.assignment.feature.domain.GetPagedPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesPagedViewModel @Inject constructor(
    private val pagedCallback: PageLoadingMoviesCallback,
    private val pagedConfig: PagedList.Config,
    private val pagedUseCase: GetPagedPopularMoviesUseCase,
) : ViewModel() {

    private lateinit var moviesData: LiveData<PagedList<EntityMovieItem>>
    var movies: MediatorLiveData<PagedList<EntityMovieItem>> = MediatorLiveData()

    init {
        createDataSource()
    }

    private fun createDataSource() {
        moviesData = LivePagedListBuilder(pagedUseCase.execute(), pagedConfig)
            .setBoundaryCallback(pagedCallback)
            .build()

        movies.addSource(moviesData) { newData ->
            movies.value = newData
        }
    }

    fun refreshMovies() {
        movies.removeSource(moviesData)
        createDataSource()
    }
}
