package com.anibalbastias.moviesapp.feature.data.remote

import com.anibalbastias.moviesapp.feature.data.remote.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteMoviesService {

    @GET("movie/{movie_type}")
    suspend fun getMoviesByType(
        @Path("movie_type") movieType: String = "now_playing",
        @Query("page") page: String = "undefined",
    ): Response<RemoteMovieData>

    @GET("movie/popular")
    suspend fun getPagedMovies(
        @Query("page") page: Int,
    ): RemoteMovieData

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: String = "",
    ): RemoteMovieDetail

    @GET("search/movie")
    suspend fun searchPagedMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): RemoteMovieData

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideosById(
        @Path("movie_id") movieId: String = "",
    ): RemoteMovieVideos

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCreditsById(
        @Path("movie_id") movieId: String = "",
    ): RemoteMovieCredits

    @GET("movie/{movie_id}/watch/providers")
    suspend fun getMovieProvidersById(
        @Path("movie_id") movieId: String = "",
    ): RemoteMovieProviders

    @GET("movie/{movie_id}/similar")
    suspend fun getMovieSimilarById(
        @Path("movie_id") movieId: String = "",
    ): RemoteMovieData

    @GET("movie/{movie_id}/translations")
    suspend fun getMovieTranslationsById(
        @Path("movie_id") movieId: String = "",
    ): RemoteMovieTranslations
}