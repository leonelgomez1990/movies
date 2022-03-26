package com.lgomez.movies.data.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Genres(
    var id: Int,
    var name: String
) : Parcelable

fun Genres.toList() = name