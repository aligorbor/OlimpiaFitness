package com.example.olimpiafitness.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.olimpiafitness.data.model.Lesson
import com.example.olimpiafitness.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainViewModel (private val mainRepository: MainRepository) : ViewModel(),
    CoroutineScope by MainScope() {

    private var _listLessons = MutableLiveData<ResponseState<List<Lesson>>>()
    val listLessons: LiveData<ResponseState<List<Lesson>>>
        get() = _listLessons

    fun getSchedule() {
        _listLessons.value = ResponseState.Loading
        launch(Dispatchers.IO) {
            try {
                val listOfLessons: List<Lesson> = mainRepository.getSchedule().lessons.toList()
                _listLessons.postValue(ResponseState.Success(listOfLessons))
            } catch (exception: Exception) {
                _listLessons.postValue(ResponseState.Error(exception))
            }
        }
    }

}