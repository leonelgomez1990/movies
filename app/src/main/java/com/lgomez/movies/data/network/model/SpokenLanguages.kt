package com.lgomez.movies.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class SpokenLanguages(
    @SerializedName("iso_639_1")
    var code: String,
    var name: String
) : Parcelable