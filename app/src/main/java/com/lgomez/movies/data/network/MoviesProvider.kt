package com.lgomez.movies.data.network

import com.lgomez.movies.data.network.model.MoviesAPIResponse
import retrofit2.http.GET

private const val API_KEY = "0d4a85fde806c8c22ed4b13ac766d3ff"

interface MoviesProvider {
    @GET("movie/popular?api_key=$API_KEY&language=en-US&page=1")
    suspend fun getPopularMovies(): MoviesAPIResponse
}