package com.example.currencyconverter.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.domain.model.Currencies
import com.example.currencyconverter.domain.repository.Repository
import com.example.currencyconverter.ui.mapper.CurrencyUiModelMapper
import com.example.currencyconverter.ui.model.CurrenciesUiModel
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() { // надстройка с бизнеслогикой

    val liveData = MutableLiveData<CurrenciesUiModel>()



    fun init() {
        viewModelScope.launch {
            repository.getCurrencies()?.let {
                liveData.postValue(CurrencyUiModelMapper.mapDomainModelToUiModel(it)) // возвращает
            }
        }
    }
}

// переопределить фабрику viewModel - не будет падать