package com.example.currencyconverter.ui.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.Repository
import com.example.currencyconverter.data.model.dto.HistoryDb
import kotlinx.coroutines.launch

class HistoryViewModel(repository: Repository): ViewModel() {
    var historyList: MutableLiveData<List<HistoryDb>> = MutableLiveData()

    init {
        viewModelScope.launch {
            historyList.postValue(repository.getHistory())
        }
    }

}