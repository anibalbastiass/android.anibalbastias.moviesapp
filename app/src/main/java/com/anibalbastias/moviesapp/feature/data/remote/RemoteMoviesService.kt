package com.anibalbastias.moviesapp.feature.data.remote

import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteMovieData
import com.anibalbastias.moviesapp.feature.data.remote.model.RemoteMovieDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteMoviesService {

    @GET("movie/{movie_type}")
    suspend fun getMoviesByType(
        @Path("movie_type") movieType: String = "now_playing",
        @Query("page") page: String = "undefined"
    ): Response<RemoteMovieData>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: String = ""
    ): Response<RemoteMovieDetail>
}