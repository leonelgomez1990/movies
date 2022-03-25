package com.lgomez.movies.data.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class ProductionCompanies(
    var name: String,
    var id: Int,
    @SerializedName("logo_path")
    var logoPath: String?,
    @SerializedName("origin_country")
    var originCountry: String
) : Parcelable