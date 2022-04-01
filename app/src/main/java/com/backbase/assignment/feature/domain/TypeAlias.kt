package com.backbase.assignment.feature.domain

import com.backbase.assignment.feature.data.remote.state.APIState
import com.backbase.assignment.feature.domain.model.DomainMovieDetail
import com.backbase.assignment.feature.domain.model.DomainMovieItem
import com.backbase.assignment.feature.presentation.model.UiMovieDetail
import com.backbase.assignment.feature.presentation.model.UiMovieItem

typealias DomainMovieDataState = APIState<List<DomainMovieItem>>
typealias DomainMovieDetailDataState = APIState<DomainMovieDetail>

typealias UiMovieDataState = APIState<List<UiMovieItem>>
typealias UiMovieDetailDataState = APIState<UiMovieDetail>
