package com.backbase.assignment.feature.data

import com.backbase.assignment.feature.data.model.list.MovieData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteMoviesService {

    @GET("movie/{movie_type}")
    suspend fun getMoviesByType(
        @Path("movie_type") movieType: String = "now_playing",
        @Query("page") page: String = "undefined"
    ): Response<MovieData>
}