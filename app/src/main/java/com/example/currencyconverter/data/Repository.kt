package com.example.currencyconverter.data

import com.example.currencyconverter.repository.LocalDataSource
import com.example.currencyconverter.repository.RemoteDataSource
import com.example.currencyconverter.data.model.Currency
import com.example.currencyconverter.data.model.Currencies
import com.example.currencyconverter.data.model.dto.CurrencyDb
import com.example.currencyconverter.data.model.dto.HistoryDb


class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

suspend fun getCurrencies(): Currencies {
    val localData = localDataSource.loadCurrencies()
    if (localData != null) {
        if (localData.isNotEmpty()) {
            var currencies = CurrencyMapper.currenciesDbToCurrencies(localData)
            return if (currencies.rates.isNotEmpty()) {
                currencies
            } else {
                   val remoteData = remoteDataSource.loadCurrencies()
                   val currenciesForDb = CurrencyMapper.mapResponseToModel(remoteData)
                   localDataSource.updateCurrencies(currenciesForDb)
                   currencies = getCurrencies()
                currencies
            }
        } else {
               val remoteData = remoteDataSource.loadCurrencies()
               val currenciesForDb = CurrencyMapper.mapResponseToModel(remoteData)
               localDataSource.updateCurrencies(currenciesForDb)
            return getCurrencies()
        }
    }
    return Currencies()
}
    fun getHistory(): List<HistoryDb>? {
        return localDataSource.loadHistory()
    }

    fun addToHistory(historyItem: HistoryDb) {
        localDataSource.addToHistory(historyItem)
    }

}


object CurrencyMapper {
    fun mapResponseToModel(response: CurrencyResponse): Currencies {
        return Currencies(
            timestamp = response.timestamp,
            base = response.base,
            date = response.date,
            rates = response.rates.toList().map {
                Currency(
                    name = it.first,
                    value = it.second,
                )
            }
        )
    }

    fun currenciesDbToCurrencies(CurrenciesDbList: List<CurrencyDb>): Currencies {
        val currencies: MutableList<Currency> = mutableListOf()
        CurrenciesDbList.forEach {
            currencies.add(
                Currency(
                    name = it.name,
                    value = it.value,
                    like = it.like
                )
            )
        }
        return Currencies(
            timestamp =  CurrenciesDbList[0].timestamp,
            base = CurrenciesDbList[0].base,
            date = CurrenciesDbList[0].date,
            rates = currencies.toList()
        )
    }
}
