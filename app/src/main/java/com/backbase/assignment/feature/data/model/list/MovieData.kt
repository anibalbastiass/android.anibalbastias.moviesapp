package com.backbase.assignment.feature.data.model.list

import com.squareup.moshi.Json

data class MovieData(
    @Json(name = "dates") val dates: MovieDates = MovieDates(),
    @Json(name = "page") val page: Int = 0,
    @Json(name = "results") val results: List<MovieResult> = listOf(),
    @Json(name = "total_pages") val totalPages: Int = 0,
    @Json(name = "total_results") val totalResults: Int = 0
)