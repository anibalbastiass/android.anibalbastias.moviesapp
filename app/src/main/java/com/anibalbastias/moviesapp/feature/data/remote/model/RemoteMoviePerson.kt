package com.anibalbastias.moviesapp.feature.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMoviePerson(
    @Json(name = "adult") val adult: Boolean? = false,
    @Json(name = "also_known_as") val alsoKnownAs: List<String>? = listOf(),
    @Json(name = "biography") val biography: String? = "",
    @Json(name = "birthday") val birthday: String? = "",
    @Json(name = "deathday") val deathDay: String? = "",
    @Json(name = "gender") val gender: Int? = 0,
    @Json(name = "homepage") val homepage: String? = "",
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "imdb_id") val imdbId: String? = "",
    @Json(name = "known_for_department") val knownForDepartment: String? = "",
    @Json(name = "name") val name: String? = "",
    @Json(name = "place_of_birth") val placeOfBirth: String? = "",
    @Json(name = "popularity") val popularity: Double? = 0.0,
    @Json(name = "profile_path") val profilePath: String? = "",
)