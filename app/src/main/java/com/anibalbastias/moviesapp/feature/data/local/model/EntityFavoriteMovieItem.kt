package com.anibalbastias.moviesapp.feature.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anibalbastias.moviesapp.feature.data.local.model.DBConstants.FAVORITE_MOVIES_TABLE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ORIGINAL_TITLE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.POSTER_PATH
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.RELEASE_DATE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.VOTE_AVERAGE

@Entity(tableName = FAVORITE_MOVIES_TABLE)
data class EntityFavoriteMovieItem(
    @PrimaryKey @ColumnInfo(name = ID) var id: String,
    @ColumnInfo(name = POSTER_PATH) val posterPath: String,
    @ColumnInfo(name = ORIGINAL_TITLE) val originalTitle: String,
    @ColumnInfo(name = VOTE_AVERAGE) val voteAverage: Double,
    @ColumnInfo(name = RELEASE_DATE) val releaseDate: String
)
