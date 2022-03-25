package com.lgomez.movies.data.network

import com.lgomez.movies.data.network.model.MovieDetailsResponse
import com.lgomez.movies.data.network.model.MoviesAPIResponse
import retrofit2.http.GET
import retrofit2.http.Path

private const val API_KEY = "0d4a85fde806c8c22ed4b13ac766d3ff"

interface MoviesProvider {
    @GET("movie/popular?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getPopularMovies(): MoviesAPIResponse

    @GET("movie/{movie}?api_key=$API_KEY&language=en-US")
    suspend fun getDetailsMovie(@Path("movie") movieId: Int) : MovieDetailsResponse
}