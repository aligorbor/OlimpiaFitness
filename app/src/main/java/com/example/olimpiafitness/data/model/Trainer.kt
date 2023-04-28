package com.example.olimpiafitness.data.model

import com.google.gson.annotations.SerializedName

data class Trainer(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)
