package com.example.currencyconverter.data

import com.example.currencyconverter.data.model.Currency
import java.util.*

data class CurrencyResponse(
    val timestamp: Long,
    val base: String,
    val date: String,
    val rates: Map<String, Double>

)