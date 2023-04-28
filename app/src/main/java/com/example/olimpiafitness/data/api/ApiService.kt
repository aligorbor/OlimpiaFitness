package com.example.olimpiafitness.data.api

import com.example.olimpiafitness.data.model.Schedule
import retrofit2.http.*

interface ApiService {
    @GET(endPointSchedule)
    suspend fun getSchedule(@Query(paramClub_id) clubId: Int): Schedule

}