package com.lgomez.movies.ui.model

import android.os.Parcelable
import com.lgomez.movies.domain.model.DetailsMovie
import kotlinx.parcelize.Parcelize

@Parcelize
class MovieUI(
    var id: Int,
    var title: String,
    var cover: String,
    var genres: List<String>,
    var originalLanguage: String,
    val popularity: Int,
    val releaseDate: String
) : Parcelable {
    constructor(id: Int) : this(id, "", "", listOf(), "", 0, "")
}

fun DetailsMovie.toMovieUI(): MovieUI =
    MovieUI(id, title, cover, genres, originalLanguage, popularity, releaseDate)