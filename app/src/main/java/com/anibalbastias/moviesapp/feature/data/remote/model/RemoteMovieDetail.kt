package com.anibalbastias.moviesapp.feature.data.remote.model

import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ADULT
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.BACKDROP_PATH
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.BELONGS_TO_COLLECTION
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.BUDGET
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ENGLISH_NAME
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.GENRES
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.HOMEPAGE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.IMDB_ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ISO_3166_1
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ISO_639_1
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.LOGO_PATH
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.NAME
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ORIGINAL_LANGUAGE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ORIGINAL_TITLE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ORIGIN_COUNTRY
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.OVERVIEW
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.POPULARITY
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.POSTER_PATH
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.PRODUCTION_COMPANIES
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.PRODUCTION_COUNTRIES
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.RELEASE_DATE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.REVENUE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.RUNTIME
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.SPOKEN_LANGUAGES
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.STATUS
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.TAGLINE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.TITLE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.VIDEO
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.VOTE_AVERAGE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.VOTE_COUNT
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieDetail(
    @Json(name = ADULT) override val adult: Boolean? = false,
    @Json(name = BACKDROP_PATH) val backdropPath: String? = "",
    @Json(name = BELONGS_TO_COLLECTION) val belongsToCollection: RemoteBelongsToCollection? = RemoteBelongsToCollection(),
    @Json(name = BUDGET) val budget: Int? = 0,
    @Json(name = GENRES) val genres: List<RemoteGenreMovieItem>? = listOf(),
    @Json(name = HOMEPAGE) val homepage: String? = "",
    @Json(name = ID) override val id: Long? = 0,
    @Json(name = IMDB_ID) val imdbId: String? = "",
    @Json(name = ORIGINAL_LANGUAGE) override val originalLanguage: String? = "",
    @Json(name = ORIGINAL_TITLE) override val originalTitle: String? = "",
    @Json(name = OVERVIEW) override val overview: String? = "",
    @Json(name = POPULARITY) override val popularity: Double? = 0.0,
    @Json(name = POSTER_PATH) override val posterPath: String = "",
    @Json(name = PRODUCTION_COMPANIES) val productionCompanies: List<RemoteProductionCompany>? = listOf(),
    @Json(name = PRODUCTION_COUNTRIES) val productionCountries: List<RemoteProductionCountry>? = listOf(),
    @Json(name = RELEASE_DATE) override val releaseDate: String,
    @Json(name = REVENUE) val revenue: Int? = 0,
    @Json(name = RUNTIME) val runtime: Int? = 0,
    @Json(name = SPOKEN_LANGUAGES) val spokenLanguages: List<RemoteSpokenLanguage>? = listOf(),
    @Json(name = STATUS) val status: String? = "",
    @Json(name = TAGLINE) val tagline: String? = "",
    @Json(name = TITLE) override val title: String? = "",
    @Json(name = VIDEO) override val video: Boolean? = false,
    @Json(name = VOTE_AVERAGE) override val voteAverage: Double? = 0.0,
    @Json(name = VOTE_COUNT) override val voteCount: Int? = 0,
) : RemoteMovieResult()

@JsonClass(generateAdapter = true)
data class RemoteSpokenLanguage(
    @Json(name = ENGLISH_NAME) val englishName: String? = "",
    @Json(name = ISO_639_1) val iso6391: String? = "",
    @Json(name = NAME) val name: String? = "",
)

@JsonClass(generateAdapter = true)
data class RemoteGenreMovieItem(
    @Json(name = ID) val id: Int? = 0,
    @Json(name = NAME) val name: String? = "",
)

@JsonClass(generateAdapter = true)
data class RemoteProductionCountry(
    @Json(name = ISO_3166_1) val iso31661: String? = "",
    @Json(name = NAME) val name: String? = "",
)

@JsonClass(generateAdapter = true)
data class RemoteProductionCompany(
    @Json(name = ID) val id: Int? = 0,
    @Json(name = LOGO_PATH) val logoPath: String? = "",
    @Json(name = NAME) val name: String? = "",
    @Json(name = ORIGIN_COUNTRY) val originCountry: String? = "",
)

@JsonClass(generateAdapter = true)
data class RemoteBelongsToCollection(
    @Json(name = BACKDROP_PATH) val backdropPath: String? = "",
    @Json(name = ID) val id: Int? = 0,
    @Json(name = NAME) val name: String? = "",
    @Json(name = POSTER_PATH) val posterPath: String? = "",
)