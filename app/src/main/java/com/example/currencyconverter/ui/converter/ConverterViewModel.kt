package com.example.currencyconverter.ui.converter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyconverter.data.Repository
import com.example.currencyconverter.data.model.dto.HistoryDb

class ConverterViewModel(private val repository: Repository): ViewModel() {
    var from: MutableLiveData<String> = MutableLiveData()
    var to: MutableLiveData<String> = MutableLiveData()
    var toVal: MutableLiveData<Double> = MutableLiveData()
    var fromVal: MutableLiveData<Double> = MutableLiveData()
    var rate: MutableLiveData<Double> = MutableLiveData()


    fun currencyUpdateFrom(from: String?) {
        this.from.value = from
    }

    fun currencyUpdateTo(to: String?) {
        this.to.value = to
    }

    fun valueUpdateFrom(from: String?) {
        if (from != null) {
            this.fromVal.value = from.toDouble()
        }
    }

    fun valueUpdateTo(from: String?) {
        if (from?.toDouble() != null) {
            this.toVal.value = "%.2f".format(from.let {
                this.rate.value?.times(it.toDouble())
            }).replace(",",".").toDouble()
        }
    }

    fun updateRate(rate: Double?) {
        this.rate.value = rate
    }

    fun addToHistory() {
        repository.addToHistory(
            HistoryDb(
                0,
                from.value.toString(),
                fromVal.value!!,
                to.value.toString(),
                toVal.value!!
            )
        )
    }


    init {
        from.value = ""
        to.value = ""
        toVal.value = 0.0
        rate.value = 0.0
    }

}