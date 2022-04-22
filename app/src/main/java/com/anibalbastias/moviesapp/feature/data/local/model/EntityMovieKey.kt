package com.anibalbastias.moviesapp.feature.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anibalbastias.moviesapp.feature.data.local.model.DBConstants.MOVIE_REMOTE_KEY_TABLE

@Entity(tableName = MOVIE_REMOTE_KEY_TABLE)
data class EntityMovieKey(
    @PrimaryKey
    val id: String,
    val previousPage: Int?,
    val nextPage: Int?
)