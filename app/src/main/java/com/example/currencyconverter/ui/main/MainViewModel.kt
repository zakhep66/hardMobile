package com.example.currencyconverter.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverter.data.CurrencyResponse
import com.example.currencyconverter.data.Dependencies
import com.example.currencyconverter.data.Repository
import com.example.currencyconverter.data.model.Currencies
import kotlinx.coroutines.launch

class MainViewModel (repository: Repository) : ViewModel() {
    var currencyResponse: MutableLiveData<CurrencyResponse> = MutableLiveData()
    var currenciesData: MutableLiveData<Currencies> = MutableLiveData()

init {
    var currencies: Currencies?
    viewModelScope.launch {
        currencies = repository.getCurrencies()
        currenciesData.postValue(currencies)
    }
}

}