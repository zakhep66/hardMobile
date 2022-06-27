package com.example.currencyconverter.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.Repository
import com.example.currencyconverter.data.model.dto.HistoryDb
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.OffsetDateTime

class HistoryViewModel(private val repository: Repository): ViewModel() {
    var historyList: MutableLiveData<List<HistoryDb>> = MutableLiveData()

    init {
        /*viewModelScope.launch {
            historyList.postValue(repository.getHistory())
        }*/
    }

    fun getHistory() {
        viewModelScope.launch {
            historyList.postValue(repository.getHistory())
        }
    }

    fun getDateFilteredHistory(dateFrom: LocalDateTime, dateTo: LocalDateTime){
        viewModelScope.launch {
            val dateFromTimestamp = localDateTimeToTimestamp(dateFrom) * 1000
            val dateToTimestamp = localDateTimeToTimestamp(dateTo) * 1000
            historyList.postValue(repository.getDateFilteredHistory(dateFromTimestamp, dateToTimestamp))
        }
    }

    private fun localDateTimeToTimestamp(date: LocalDateTime): Long =
        date.toEpochSecond(OffsetDateTime.now().offset)

}