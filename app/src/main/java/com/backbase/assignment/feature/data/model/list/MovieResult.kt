package com.backbase.assignment.feature.data.model.list

import com.squareup.moshi.Json

data class MovieResult(
    @Json(name = "adult") val adult: Boolean = false,
    @Json(name = "backdrop_path") val backdropPath: String = "",
    @Json(name = "genre_ids") val genreIds: List<Int> = listOf(),
    @Json(name = "id") val id: Int = 0,
    @Json(name = "original_language") val originalLanguage: String = "",
    @Json(name = "original_title") val originalTitle: String = "",
    @Json(name = "overview") val overview: String = "",
    @Json(name = "popularity") val popularity: Double = 0.0,
    @Json(name = "poster_path") val posterPath: String = "",
    @Json(name = "release_date") val releaseDate: String = "",
    @Json(name = "title") val title: String = "",
    @Json(name = "video") val video: Boolean = false,
    @Json(name = "vote_average") val voteAverage: Double = 0.0,
    @Json(name = "vote_count") val voteCount: Int = 0
)