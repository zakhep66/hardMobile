package com.example.currencyconverter.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.Repository

class HistoryViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java))
            return HistoryViewModel(repository) as T
        throw IllegalArgumentException("ViewModel not found!")
    }
}