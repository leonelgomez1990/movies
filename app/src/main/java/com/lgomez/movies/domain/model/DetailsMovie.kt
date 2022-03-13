package com.lgomez.movies.domain.model

data class DetailsMovie(
    var id: Int,
    var title: String,
    var cover: String,
    var genres: List<String>,
    var originalLanguage: String,
    val popularity: Int,
    val releaseDate: String
)
