package com.anibalbastias.moviesapp.feature.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anibalbastias.moviesapp.feature.data.local.model.DBConstants.FAVORITE_MOVIES_TABLE
import com.anibalbastias.moviesapp.feature.data.local.model.DBConstants.SAVED_MOVIES_TABLE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ID
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.ORIGINAL_TITLE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.POSTER_PATH
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.RELEASE_DATE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.TITLE
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteConstants.VOTE_AVERAGE

@Entity(tableName = SAVED_MOVIES_TABLE)
data class EntitySavedMovieItem(
    @PrimaryKey @ColumnInfo(name = TITLE) val title: String,
    @ColumnInfo(name = RELEASE_DATE) val createdAt: Long,
)
