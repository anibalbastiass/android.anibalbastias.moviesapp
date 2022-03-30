package com.backbase.assignment.feature.data

import com.backbase.assignment.feature.data.model.list.MovieData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteMoviesService {

    @GET("movies/{movie_type}")
    suspend fun getMoviesByType(
        @Query("page") page: String = "undefined",
        @Path("movie_type") movieType: String = "now_playing"
    ): Response<MovieData>
}