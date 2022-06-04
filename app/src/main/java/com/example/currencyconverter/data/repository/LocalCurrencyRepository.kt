package com.example.currencyconverter.data.repository

import com.example.currencyconverter.domain.model.Currency

interface LocalCurrencyRepository {

    suspend fun getRoomCurrencyList(): MutableList<Currency>

    suspend fun getFavoriteCurrencyList(): MutableList<Currency>?

    suspend fun insertCurrencyList(currency: Currency, onSuccess:() -> Unit)

    suspend fun updateListFavoriteCurrency(currency: Currency, onSuccess:() -> Unit)

    suspend fun updateListCurrency(currency: Currency, onSuccess:() -> Unit)
}