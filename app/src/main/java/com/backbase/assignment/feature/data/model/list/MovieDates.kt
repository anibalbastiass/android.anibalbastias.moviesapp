package com.backbase.assignment.feature.data.model.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDates(
    @Json(name = "maximum") val maximum: String = "",
    @Json(name = "minimum") val minimum: String = ""
)