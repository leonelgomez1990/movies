package com.lgomez.movies.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.lgomez.movies.domain.model.PopularMovies
import kotlinx.parcelize.Parcelize

/*
From: https://developers.themoviedb.org/3/movies/get-popular-movies
 */

private const val POSTER_URL = "https://image.tmdb.org/t/p/w500"

@Parcelize
class MovieAPIModel(
    @SerializedName("poster_path")
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double
) : Parcelable

fun MovieAPIModel.toPopularMovie(): PopularMovies =
    PopularMovies(id, title, POSTER_URL + posterPath)