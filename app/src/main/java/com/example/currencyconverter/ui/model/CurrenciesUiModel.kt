package com.example.currencyconverter.ui.model

import com.example.currencyconverter.domain.model.Currency


data class CurrenciesUiModel(
    val currencyList: List<Currency>,
    val isLoading: Boolean
)
