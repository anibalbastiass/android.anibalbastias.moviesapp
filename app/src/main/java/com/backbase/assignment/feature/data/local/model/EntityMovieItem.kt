package com.backbase.assignment.feature.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.backbase.assignment.feature.data.local.model.DBConstants.INDEX
import com.backbase.assignment.feature.data.local.model.DBConstants.MOVIES_TABLE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.ID
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.ORIGINAL_TITLE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.POSTER_PATH
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.RELEASE_DATE
import com.backbase.assignment.feature.data.remote.model.RemoteConstants.VOTE_AVERAGE

@Entity(tableName = MOVIES_TABLE)
data class EntityMovieItem(
    @PrimaryKey @ColumnInfo(name = INDEX) var index: Long = 0,
    @ColumnInfo(name = ID) var movieId: Long = 0,
    @ColumnInfo(name = POSTER_PATH) val posterPath: String = "",
    @ColumnInfo(name = ORIGINAL_TITLE) val originalTitle: String = "",
    @ColumnInfo(name = VOTE_AVERAGE) val voteAverage: Double = 0.0,
    @ColumnInfo(name = RELEASE_DATE) val releaseDate: String = "",
)
