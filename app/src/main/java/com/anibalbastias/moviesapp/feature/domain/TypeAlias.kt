package com.anibalbastias.moviesapp.feature.domain

import com.anibalbastias.moviesapp.feature.data.remote.state.APIState
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieDetail
import com.anibalbastias.moviesapp.feature.domain.model.DomainMovieItem
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieDetail
import com.anibalbastias.moviesapp.feature.presentation.model.UiMovieItem

typealias DomainMovieDataState = APIState<List<DomainMovieItem>>
typealias DomainMovieDetailDataState = APIState<DomainMovieDetail>

typealias UiMovieDataState = APIState<List<UiMovieItem>>
typealias UiMovieDetailDataState = APIState<UiMovieDetail>
