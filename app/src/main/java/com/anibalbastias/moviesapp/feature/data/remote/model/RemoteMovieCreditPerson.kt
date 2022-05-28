package com.anibalbastias.moviesapp.feature.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieCreditPerson(
    @Json(name = "id") val id: Int? = 0,
    @Json(name = "cast") val cast: List<RemoteMovieResult>? = listOf(),
    @Json(name = "crew") val crew: List<RemoteMovieResult>? = listOf(),
)