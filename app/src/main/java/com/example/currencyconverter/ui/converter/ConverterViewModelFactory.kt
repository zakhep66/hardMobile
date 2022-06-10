package com.example.currencyconverter.ui.converter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.Repository

class ConverterViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConverterViewModel::class.java))
            return ConverterViewModel(repository) as T
        throw IllegalArgumentException("ViewModel not found!")
    }
}