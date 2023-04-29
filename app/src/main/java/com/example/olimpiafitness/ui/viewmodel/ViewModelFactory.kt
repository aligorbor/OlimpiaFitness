package com.example.olimpiafitness.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.olimpiafitness.data.api.ApiService
import com.example.olimpiafitness.data.repository.MainRepository

class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(MainRepository(apiService)) as T
        }
        throw java.lang.IllegalArgumentException("Unknown class name")
    }
}