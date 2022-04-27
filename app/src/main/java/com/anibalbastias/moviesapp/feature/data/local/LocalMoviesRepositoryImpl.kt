package com.anibalbastias.moviesapp.feature.data.local

import androidx.paging.PagingSource
import com.anibalbastias.moviesapp.feature.data.local.dao.FavoritesDao
import com.anibalbastias.moviesapp.feature.data.local.dao.MoviesDao
import com.anibalbastias.moviesapp.feature.data.local.model.EntityFavoriteMovieItem
import com.anibalbastias.moviesapp.feature.data.local.model.EntityMovieItem
import com.anibalbastias.moviesapp.feature.domain.repository.LocalMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalMoviesRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao,
    private val favoriteDao: FavoritesDao
) : LocalMoviesRepository {

    override fun getPopularMovies(): PagingSource<Int, EntityMovieItem> = moviesDao.getAllMovies()

    override fun getFavoriteMovies(): Flow<List<EntityFavoriteMovieItem>> = flow {
        emit(favoriteDao.getFavoriteList())
    }

    override suspend fun addFavoriteMovie(movie: EntityFavoriteMovieItem): Flow<Boolean> = flow {
        favoriteDao.addFavorite(movie)
        emit(true)
    }

    override suspend fun removeFavoriteMovie(movie: EntityFavoriteMovieItem): Flow<Boolean> = flow {
        favoriteDao.removeFavoriteMovies(movie)
        emit(true)
    }
}
