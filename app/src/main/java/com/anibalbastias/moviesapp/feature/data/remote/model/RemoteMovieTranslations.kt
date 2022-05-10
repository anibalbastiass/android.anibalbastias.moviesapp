package com.anibalbastias.moviesapp.feature.data.remote.model

import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.DATA
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ENGLISH_NAME
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.HOMEPAGE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ISO_3166_1
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ISO_639_1
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.NAME
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.OVERVIEW
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.RUNTIME
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.TAGLINE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.TITLE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.TRANSLATIONS
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieTranslations(
    @Json(name = ID) val id: Int? = 0,
    @Json(name = TRANSLATIONS) val translations: List<RemoteMovieTranslationItem>? = listOf(),
)

@JsonClass(generateAdapter = true)
data class RemoteMovieTranslationItem(
    @Json(name = DATA) val translationData: RemoteMovieTranslationData? = RemoteMovieTranslationData(),
    @Json(name = ENGLISH_NAME) val englishName: String? = "",
    @Json(name = ISO_3166_1) val iso31661: String? = "",
    @Json(name = ISO_639_1) val iso6391: String? = "",
    @Json(name = NAME) val name: String? = "",
)

@JsonClass(generateAdapter = true)
data class RemoteMovieTranslationData(
    @Json(name = HOMEPAGE) val homepage: String? = "",
    @Json(name = OVERVIEW) val overview: String? = "",
    @Json(name = RUNTIME) val runtime: Int? = 0,
    @Json(name = TAGLINE) val tagline: String? = "",
    @Json(name = TITLE) val title: String? = "",
)