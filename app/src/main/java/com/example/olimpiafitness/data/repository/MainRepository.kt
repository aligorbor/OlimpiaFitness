package com.example.olimpiafitness.data.repository

import com.example.olimpiafitness.data.api.ApiService

class MainRepository(private  val  apiService: ApiService) {
    suspend fun getSchedule(clubId: Int = 2) = apiService.getSchedule(clubId)
}