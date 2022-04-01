package com.example.currencyconverter.domain.model

import java.util.*

data class Currencies(
    val date: Date,
    val base: String,
    val rates: List<Currency>
)

data class Currency(
    val name: String,
    val value: Double
)