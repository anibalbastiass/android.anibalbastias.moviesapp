package com.anibalbastias.moviesapp.feature.data.remote.model

import android.provider.ContactsContract.CommonDataKinds.Organization.DEPARTMENT
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ADULT
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.CAST
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.CAST_ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.CHARACTER
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.CREDIT_ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.CREW
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.GENDER
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.JOB
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.KNOWN_FOR_DEPARTMENT
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.NAME
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ORDER
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ORIGINAL_NAME
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.POPULARITY
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.PROFILE_PATH
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteMovieCredits(
    @Json(name = ID) val id: Int? = 0,
    @Json(name = CAST) val cast: List<RemoteMovieCastItem>? = listOf(),
    @Json(name = CREW) val crew: List<RemoteMovieCastItem>? = listOf(),
)

@JsonClass(generateAdapter = true)
data class RemoteMovieCastItem(
    @Json(name = ID) val id: Int? = 0,
    @Json(name = ADULT) val adult: Boolean? = false,
    @Json(name = CREDIT_ID) val creditId: String? = "",
    @Json(name = DEPARTMENT) val department: String? = "",
    @Json(name = GENDER) val gender: Int? = 0,
    @Json(name = JOB) val job: String? = "",
    @Json(name = KNOWN_FOR_DEPARTMENT) val knownForDepartment: String? = "",
    @Json(name = NAME) val name: String? = "",
    @Json(name = ORIGINAL_NAME) val originalName: String? = "",
    @Json(name = POPULARITY) val popularity: Double? = 0.0,
    @Json(name = PROFILE_PATH) val profilePath: Any?,
    @Json(name = CAST_ID) val castId: Int? = 0,
    @Json(name = CHARACTER) val character: String? = "",
    @Json(name = ORDER) val order: Int? = 0,
)
