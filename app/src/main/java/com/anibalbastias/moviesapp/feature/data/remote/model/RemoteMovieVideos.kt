package com.anibalbastias.moviesapp.feature.data.remote.model

import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ISO_3166_1
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ISO_639_1
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.KEY
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.NAME
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.OFFICIAL
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.PUBLISHED_AT
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.RESULTS
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.SITE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.SIZE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.TYPE
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieVideos(
    @Json(name = ID) val id: Int? = 0,
    @Json(name = RESULTS) val results: List<RemoteMovieVideoItem>? = listOf(),
)

@JsonClass(generateAdapter = true)
data class RemoteMovieVideoItem(
    @Json(name = ID) val id: String? = "",
    @Json(name = ISO_3166_1) val iso31661: String? = "",
    @Json(name = ISO_639_1) val iso6391: String? = "",
    @Json(name = KEY) val key: String? = "",
    @Json(name = NAME) val name: String? = "",
    @Json(name = OFFICIAL) val official: Boolean? = false,
    @Json(name = PUBLISHED_AT) val publishedAt: String? = "",
    @Json(name = SITE) val site: String? = "",
    @Json(name = SIZE) val size: Int? = 0,
    @Json(name = TYPE) val type: String? = "",
)