package com.lgomez.movies.data.network

import com.lgomez.movies.data.network.model.MovieDetailsResponse
import com.lgomez.movies.data.network.model.MoviesAPIResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesProvider {
    @GET("movie/popular?")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") languageCode: String,
        @Query("page") page: Int
    ): MoviesAPIResponse

    @GET("movie/{movie}?")
    suspend fun getDetailsMovie(
        @Path("movie") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") languageCode: String
    ): MovieDetailsResponse

    @GET("configuration/primary_translations?")
    suspend fun getPrimaryTranslations(
        @Query("api_key") apiKey: String
    ): List<String>
}