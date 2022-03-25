package com.lgomez.movies.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class ProductionCountries(
    @SerializedName("iso_3166_1")
    var code: String,
    var name: String
) : Parcelable