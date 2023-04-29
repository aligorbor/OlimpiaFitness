package com.example.olimpiafitness.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.olimpiafitness.data.model.Lesson
import com.example.olimpiafitness.data.repository.MainRepository
import com.example.olimpiafitness.utils.coachName
import com.example.olimpiafitness.utils.dateToDayOfWeek
import com.example.olimpiafitness.utils.dateToStr
import com.example.olimpiafitness.utils.strToDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

class MainViewModel(private val mainRepository: MainRepository) : ViewModel(),
    CoroutineScope by MainScope() {

    private var _listLessons = MutableLiveData<ResponseState<List<Lesson>>>()
    val listLessons: LiveData<ResponseState<List<Lesson>>>
        get() = _listLessons

    fun getSchedule() {
        _listLessons.value = ResponseState.Loading
        launch(Dispatchers.IO) {
            try {
                val schedule = mainRepository.getSchedule()
                val listOfLessons: List<Lesson> = schedule.lessons.toMutableList().map {
                    it.toDate = strToDate("${it.date} ${it.startTime}")
                    it.toStrDate = dateToStr(it.toDate ?: Date())
                    it.dOfW = dateToDayOfWeek(it.toDate ?: Date())
                    it.coach = coachName(schedule, it.coachId)
                    it
                }.sortedBy {
                    it.toDate
                }
                _listLessons.postValue(ResponseState.Success(listOfLessons))
            } catch (exception: Exception) {
                _listLessons.postValue(ResponseState.Error(exception))
            }
        }
    }

}