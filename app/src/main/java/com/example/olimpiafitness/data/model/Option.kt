package com.example.olimpiafitness.data.model

import com.google.gson.annotations.SerializedName

data class Option(
    @SerializedName("club_name")
    val clubName: String?,
    @SerializedName("primary_color")
    val primaryColor: String?,
    @SerializedName("secondary_color")
    val secondaryColor: String?
)
