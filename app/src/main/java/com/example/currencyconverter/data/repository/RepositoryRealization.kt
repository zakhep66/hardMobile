package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.CurrencyDao
import com.example.currencyconverter.domain.model.Currency

class RepositoryRealization(private var currencyDao: CurrencyDao): LocalCurrencyRepository {


    override suspend fun getRoomCurrencyList(): MutableList<Currency> {
        return currencyDao.getRoomCurrencyList()
    }

    override suspend fun getFavoriteCurrencyList(): MutableList<Currency>? {
        return currencyDao.getFavoriteCurrencyList()
    }

    override suspend fun insertCurrencyList(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.insertCurrencyList(currency)
        onSuccess()
    }

    override suspend fun updateListFavoriteCurrency(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.updateListFavoriteCurrency(currency.name,  currency.isFavorite)
        onSuccess()
    }

    override suspend fun updateListCurrency(currency: Currency, onSuccess: () -> Unit) {
        currencyDao.updateListCurrency(currency.name,  currency.value)
        onSuccess()
    }
}