package com.backbase.assignment.feature.domain

import com.backbase.assignment.feature.data.remote.model.RemoteMovieData
import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.model.DomainMovieItem
import com.backbase.assignment.feature.presentation.model.UiMovieItem

typealias RemoteMovieDataState = APIState<RemoteMovieData>
typealias DomainMovieDataState = APIState<List<DomainMovieItem>>
typealias UiMovieDataState = APIState<List<UiMovieItem>>