package com.example.currencyconverter.data.model

import java.util.*

data class Currencies (
    val timestamp: Long = 0,
    val base: String = "",
    val date: String = "",
    var rates: List<Currency> = listOf(),
)

data class Currency (
    val name: String,
    val value: Double,
    var like: Boolean = false
)