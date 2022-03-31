package com.backbase.assignment.feature.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieDetail(
    @Json(name = "adult") override val adult: Boolean? = false,
    @Json(name = "backdrop_path") override val backdropPath: String = "",
    @Json(name = "belongs_to_collection") val belongsToCollection: RemoteBelongsToCollection? = RemoteBelongsToCollection(),
    @Json(name = "budget") val budget: Int? = 0,
    @Json(name = "genres") val genres: List<RemoteGenreMovieItem>? = listOf(),
    @Json(name = "homepage") val homepage: String? = "",
    @Json(name = "id") override val id: Long? = 0,
    @Json(name = "imdb_id") val imdbId: String? = "",
    @Json(name = "original_language") override val originalLanguage: String? = "",
    @Json(name = "original_title") override val originalTitle: String? = "",
    @Json(name = "overview") override val overview: String? = "",
    @Json(name = "popularity") override val popularity: Double? = 0.0,
    @Json(name = "poster_path") override val posterPath: String = "",
    @Json(name = "production_companies") val productionCompanies: List<RemoteProductionCompany>? = listOf(),
    @Json(name = "production_countries") val productionCountries: List<RemoteProductionCountry>? = listOf(),
    @Json(name = "release_date") override val releaseDate: String,
    @Json(name = "revenue") val revenue: Int? = 0,
    @Json(name = "runtime") val runtime: Int? = 0,
    @Json(name = "spoken_languages") val spokenLanguages: List<RemoteSpokenLanguage>? = listOf(),
    @Json(name = "status") val status: String? = "",
    @Json(name = "tagline") val tagline: String? = "",
    @Json(name = "title") override val title: String? = "",
    @Json(name = "video") override val video: Boolean? = false,
    @Json(name = "vote_average") override val voteAverage: Double? = 0.0,
    @Json(name = "vote_count") override val voteCount: Int? = 0
) : RemoteMovieResult()

@JsonClass(generateAdapter = true)
data class RemoteSpokenLanguage(
    @Json(name = "english_name") val englishName: String? = "",
    @Json(name = "iso_639_1") val iso6391: String? = "",
    @Json(name = "name") val name: String? = ""
)

data class RemoteGenreMovieItem(
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "name") val name: String? = ""
)

@JsonClass(generateAdapter = true)
data class RemoteProductionCountry(
    @Json(name = "iso_3166_1") val iso31661: String? = "",
    @Json(name = "name") val name: String? = ""
)

@JsonClass(generateAdapter = true)
data class RemoteProductionCompany(
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "logo_path") val logoPath: String? = "",
    @Json(name = "name") val name: String? = "",
    @Json(name = "origin_country") val originCountry: String? = ""
)

@JsonClass(generateAdapter = true)
data class RemoteBelongsToCollection(
    @Json(name = "backdrop_path") val backdropPath: String? = "",
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "name") val name: String? = "",
    @Json(name = "poster_path") val posterPath: String? = ""
)