package com.backbase.assignment.feature.data.remote.model

import android.annotation.SuppressLint
import com.backbase.assignment.BuildConfig
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.ADULT
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.BACKDROP_PATH
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.DATES
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.GENRE_IDS
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.ID
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.MAXIMUM
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.MINIMUM
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.ORIGINAL_LANGUAGE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.ORIGINAL_TITLE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.OVERVIEW
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.PAGE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.POPULARITY
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.POSTER_PATH
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.RELEASE_DATE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.RESULTS
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.TITLE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.TOTAL_PAGES
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.TOTAL_RESULTS
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.VIDEO
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.VOTE_AVERAGE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.VOTE_COUNT
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat
import java.util.*

@JsonClass(generateAdapter = true)
data class RemoteMovieData(
    @Json(name = DATES) val dates: RemoteMovieDates? = RemoteMovieDates(),
    @Json(name = PAGE) val page: Int? = 0,
    @Json(name = RESULTS) val results: List<RemoteMovieResult>? = listOf(),
    @Json(name = TOTAL_PAGES) val totalPages: Int? = 0,
    @Json(name = TOTAL_RESULTS) val totalResults: Int? = 0,
)

@JsonClass(generateAdapter = true)
data class RemoteMovieDates(
    @Json(name = MAXIMUM) val maximum: String? = "",
    @Json(name = MINIMUM) val minimum: String? = "",
)

@JsonClass(generateAdapter = true)
open class RemoteMovieResult(
    @Json(name = ADULT) open val adult: Boolean? = false,
    @Json(name = BACKDROP_PATH) val _backdropPath: String? = "",
    @Json(name = GENRE_IDS) open val genreIds: List<Int>? = listOf(),
    @Json(name = ID) open val id: Long? = 0,
    @Json(name = ORIGINAL_LANGUAGE) open val originalLanguage: String? = "",
    @Json(name = ORIGINAL_TITLE) open val originalTitle: String? = "",
    @Json(name = OVERVIEW) open val overview: String? = "",
    @Json(name = POPULARITY) open val popularity: Double? = 0.0,
    @Json(name = POSTER_PATH) val _posterPath: String? = "",
    @Json(name = RELEASE_DATE) val _releaseDate: String? = "",
    @Json(name = TITLE) open val title: String? = "",
    @Json(name = VIDEO) open val video: Boolean? = false,
    @Json(name = VOTE_AVERAGE) open val voteAverage: Double? = 0.0,
    @Json(name = VOTE_COUNT) open val voteCount: Int? = 0,
) {
    open val backdropPath: String = getUrlImage(_backdropPath)
    open val posterPath: String = getUrlImage(_posterPath)
    open val releaseDate: String = getFormattedDate(_releaseDate)

    private fun getUrlImage(suffix: String?) = BuildConfig.IMAGE_URL + suffix

    @SuppressLint("SimpleDateFormat")
    private fun getFormattedDate(rawDate: String?): String {
        val date: Date = SimpleDateFormat("yyyy-MM-dd").parse(rawDate)
        return SimpleDateFormat("MMMM dd, yyyy").format(date)
    }
}