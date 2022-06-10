package com.example.currencyconverter.repository

import com.example.currencyconverter.dao.CurrencyDao
import com.example.currencyconverter.dao.HistoryDao

import com.example.currencyconverter.data.model.Currencies

import com.example.currencyconverter.data.model.dto.CurrencyDb
import com.example.currencyconverter.data.model.dto.HistoryDb

class LocalDataSource(currencyDao: CurrencyDao?, historyDao: HistoryDao?) {
    private val dao = currencyDao
    private val localHistoryDao = historyDao

    suspend fun loadCurrencies(): List<CurrencyDb>? = dao?.getCurrencies()

    suspend fun updateCurrencies(currencies: Currencies) {
        dao?.deleteAll()
        currencies.rates.forEach {
            val entity = CurrencyDb.fromCurrency(it, currencies)
            dao?.insert(entity)
        }
    }

    fun loadHistory(): List<HistoryDb>? = localHistoryDao?.getHistory()

    fun addToHistory(historyItem: HistoryDb) {
        localHistoryDao?.insert(historyItem)
    }
}