package com.lgomez.movies.ui.model

import android.os.Parcelable
import com.lgomez.movies.domain.model.DetailsMovie
import com.lgomez.movies.domain.model.PopularMovies
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieUI(
    var id: Int,
    var title: String,
    var cover: String,
    var genres: List<String>,
    var originalLanguage: String,
    val popularity: Double,
    val releaseDate: String
) : Parcelable {
    constructor(id: Int) : this(id, "", "", listOf(), "", 0.0, "")
    constructor() : this(0, "", "", listOf(), "", 0.0, "")
}

fun DetailsMovie.toMovieUI(): MovieUI =
    MovieUI(id, title, cover, genres, originalLanguage, popularity, releaseDate)

fun PopularMovies.toMovieUI(): MovieUI =
    MovieUI(id, title, cover, listOf(), "", 0.0, "")

fun MovieUI.toDetailsMovie(): DetailsMovie =
    DetailsMovie(id, title, cover, genres, originalLanguage, popularity, releaseDate)

fun MovieUI.toPopularMovie(): PopularMovies =
    PopularMovies(id, title, cover)