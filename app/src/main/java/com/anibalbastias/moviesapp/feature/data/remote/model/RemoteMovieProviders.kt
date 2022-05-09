package com.anibalbastias.moviesapp.feature.data.remote.model

import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.BUY
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.DISPLAY_PRIORITY
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.FLAT_RATE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.LINK
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.LOGO_PATH
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.PROVIDER_ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.PROVIDER_NAME
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.RENT
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.RESULTS
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieProviders(
    @Json(name = ID) val id: Int? = 0,
    @Json(name = RESULTS) val results: Map<String, RemoteMovieProviderItem>? = mapOf(),
)

@JsonClass(generateAdapter = true)
data class RemoteMovieProviderItem(
    @Json(name = LINK) val link: String? = "",
    @Json(name = BUY) val buy: List<RemoteMovieProviderDesc>? = listOf(),
    @Json(name = RENT) val rent: List<RemoteMovieProviderDesc>? = listOf(),
    @Json(name = FLAT_RATE) val flatRate: List<RemoteMovieProviderDesc>? = listOf(),
)

@JsonClass(generateAdapter = true)
data class RemoteMovieProviderDesc(
    @Json(name = PROVIDER_ID) val providerId: Int? = 0,
    @Json(name = DISPLAY_PRIORITY) val displayPriority: Int? = 0,
    @Json(name = LOGO_PATH) val logoPath: String? = "",
    @Json(name = PROVIDER_NAME) val providerName: String? = "",
)