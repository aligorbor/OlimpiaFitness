package com.example.olimpiafitness.data.model


import com.google.gson.annotations.SerializedName
import java.util.*

data class Lesson(
    @SerializedName("name")
    val name: String?,
    @SerializedName("place")
    val place: String?,
    @SerializedName("coach_id")
    val coachId: String?,
    @SerializedName("startTime")
    val startTime: String?,
    @SerializedName("endTime")
    val endTime: String?,
    @SerializedName("date")
    val date: String,
    @SerializedName("tab")
    val tab: String?,
    @SerializedName("color")
    val color: String?,
    @SerializedName("tab_id")
    val tabId: Int?,
    var toDate: Date?,
    var toStrDate: String?,
    var dOfW: Int?,
    var coach: String?
)
