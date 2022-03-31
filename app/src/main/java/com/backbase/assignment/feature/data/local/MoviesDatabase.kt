package com.backbase.assignment.feature.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.backbase.assignment.feature.data.local.dao.MoviesDao
import com.backbase.assignment.feature.data.local.model.DBConstants
import com.backbase.assignment.feature.data.local.model.EntityMovieItem

@Database(
    entities = [EntityMovieItem::class],
    version = DBConstants.DATABASE_VERSION_CODE,
    exportSchema = true
)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        private var instance: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase? {
            if (instance == null) {
                synchronized(MoviesDatabase::class) {
                    instance = Room.databaseBuilder(
                        context,
                        MoviesDatabase::class.java, DBConstants.DATABASE_NAME
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }
}
