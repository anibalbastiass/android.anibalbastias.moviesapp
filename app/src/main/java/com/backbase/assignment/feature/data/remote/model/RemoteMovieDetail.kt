package com.backbase.assignment.feature.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieDetail(
    @Json(name = "adult") override val adult: Boolean,
    @Json(name = "backdrop_path") override val backdropPath: String,
    @Json(name = "belongs_to_collection") val belongsToCollection: BelongsToCollection,
    @Json(name = "budget") val budget: Int,
    @Json(name = "genres") val genres: List<RemoteGenreMovieItem>,
    @Json(name = "homepage") val homepage: String,
    @Json(name = "id") override val id: Long,
    @Json(name = "imdb_id") val imdbId: String,
    @Json(name = "original_language") override val originalLanguage: String,
    @Json(name = "original_title") override val originalTitle: String,
    @Json(name = "overview") override val overview: String,
    @Json(name = "popularity") override val popularity: Double,
    @Json(name = "poster_path") override val posterPath: String,
    @Json(name = "production_companies") val productionCompanies: List<ProductionCompany>,
    @Json(name = "production_countries") val productionCountries: List<ProductionCountry>,
    @Json(name = "release_date") override val releaseDate: String,
    @Json(name = "revenue") val revenue: Int,
    @Json(name = "runtime") val runtime: Int,
    @Json(name = "spoken_languages") val spokenLanguages: List<SpokenLanguage>,
    @Json(name = "status") val status: String,
    @Json(name = "tagline") val tagline: String,
    @Json(name = "title") override val title: String,
    @Json(name = "video") override val video: Boolean,
    @Json(name = "vote_average") override val voteAverage: Double,
    @Json(name = "vote_count") override val voteCount: Int
) : RemoteMovieResult()

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    @Json(name = "english_name") val englishName: String,
    @Json(name = "iso_639_1") val iso6391: String,
    @Json(name = "name") val name: String
)

data class RemoteGenreMovieItem(
    @Json(name = "id") val id: Int = 0,
    @Json(name = "name") val name: String = ""
)

@JsonClass(generateAdapter = true)
data class ProductionCountry(
    @Json(name = "iso_3166_1") val iso31661: String,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    @Json(name = "id") val id: Int,
    @Json(name = "logo_path") val logoPath: String,
    @Json(name = "name") val name: String,
    @Json(name = "origin_country") val originCountry: String
)

@JsonClass(generateAdapter = true)
data class BelongsToCollection(
    @Json(name = "backdrop_path") val backdropPath: String,
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "poster_path") val posterPath: String
)