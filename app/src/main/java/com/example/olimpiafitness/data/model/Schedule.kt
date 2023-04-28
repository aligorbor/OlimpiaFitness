package com.example.olimpiafitness.data.model

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("trainers")
    val trainers: Array<Trainer?>,
    @SerializedName("tabs")
    val tabs: Array<Tab?>,
    @SerializedName("lessons")
    val lessons: Array<Lesson>,
    @SerializedName("option")
    val option: Option?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Schedule

        if (!trainers.contentEquals(other.trainers)) return false
        if (!tabs.contentEquals(other.tabs)) return false
        if (!lessons.contentEquals(other.lessons)) return false
        if (option != other.option) return false

        return true
    }

    override fun hashCode(): Int {
        var result = trainers.contentHashCode()
        result = 31 * result + tabs.contentHashCode()
        result = 31 * result + lessons.contentHashCode()
        result = 31 * result + (option?.hashCode() ?: 0)
        return result
    }
}
