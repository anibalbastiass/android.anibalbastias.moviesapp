package com.backbase.assignment.feature.data.remote.model

import com.backbase.assignment.BuildConfig
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieData(
    @Json(name = "dates") val dates: RemoteMovieDates = RemoteMovieDates(),
    @Json(name = "page") val page: Int = 0,
    @Json(name = "results") val results: List<RemoteMovieResult> = listOf(),
    @Json(name = "total_pages") val totalPages: Int = 0,
    @Json(name = "total_results") val totalResults: Int = 0
)

@JsonClass(generateAdapter = true)
data class RemoteMovieDates(
    @Json(name = "maximum") val maximum: String = "",
    @Json(name = "minimum") val minimum: String = ""
)

@JsonClass(generateAdapter = true)
open class RemoteMovieResult(
    @Json(name = "adult") open val adult: Boolean = false,
    @Json(name = "backdrop_path") val _backdropPath: String = "",
    @Json(name = "genre_ids") open val genreIds: List<Int> = listOf(),
    @Json(name = "id") open val id: Long = 0,
    @Json(name = "original_language") open val originalLanguage: String = "",
    @Json(name = "original_title") open val originalTitle: String = "",
    @Json(name = "overview") open val overview: String = "",
    @Json(name = "popularity") open val popularity: Double = 0.0,
    @Json(name = "poster_path") val _posterPath: String = "",
    @Json(name = "release_date") open val releaseDate: String = "",
    @Json(name = "title") open val title: String = "",
    @Json(name = "video") open val video: Boolean = false,
    @Json(name = "vote_average") open val voteAverage: Double = 0.0,
    @Json(name = "vote_count") open val voteCount: Int = 0
) {
    open val backdropPath: String = getUrlImage(_backdropPath)
    open val posterPath: String = getUrlImage(_posterPath)

    private fun getUrlImage(suffix: String) = BuildConfig.IMAGE_URL + suffix
}