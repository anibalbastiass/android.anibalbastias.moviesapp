package com.backbase.assignment.feature.data.local.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class EntityMovieItem(
    @PrimaryKey @ColumnInfo(name = "index") var index: Long = 0,
    @ColumnInfo(name = "poster_path") val posterPath: String = "",
    @ColumnInfo(name = "original_title") val originalTitle: String = "",
    @ColumnInfo(name = "vote_average") val voteAverage: Double = 0.0,
    @ColumnInfo(name = "release_date") val releaseDate: String = "",
)
