package com.lgomez.movies.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.lgomez.movies.domain.model.DetailsMovie
import kotlinx.parcelize.Parcelize

/*
From: https://developers.themoviedb.org/3/movies/get-movie-details
 */

private const val POSTER_URL = "https://image.tmdb.org/t/p/w500"

@Parcelize
class MovieDetailsResponse(
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("belongs_to_collection")
    var belongsToCollection: BelongsToCollection?,
    var budget: Int,
    var genres: List<Genres>,
    var homepage: String?,
    var id: Int,
    @SerializedName("imdb_id")
    var imdbId: String?,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_title")
    var originalTitle: String,
    var overview: String?,
    var popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompanies>,
    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountries>,
    @SerializedName("release_date")
    var releaseDate: String,
    var revenue: Int,
    var runtime: Int?,
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguages>,
    var status: String,
    var tagline: String?,
    var title: String,
    var video: Boolean,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("vote_count")
    var voteCount: Int
) : Parcelable

fun MovieDetailsResponse.toDetailsMovie() = DetailsMovie(
    id,
    title,
    POSTER_URL + posterPath,
    genres.map { it.name },
    originalLanguage,
    popularity,
    releaseDate
)