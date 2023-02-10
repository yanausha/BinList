package com.example.binlist.data.network.models

import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("alpha2")
    val alpha2: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("emoji")
    val emoji: String?,
    @SerializedName("latitude")
    val latitude: Int?,
    @SerializedName("longitude")
    val longitude: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("numeric")
    val numeric: String?
)