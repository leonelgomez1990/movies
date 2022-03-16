package com.lgomez.movies.data.network.model

import com.google.gson.annotations.SerializedName

/*
From: https://developers.themoviedb.org/3/movies/get-popular-movies
 */
data class MoviesAPIResponse(
    val page: Int,
    val results: List<MovieAPIModel>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)