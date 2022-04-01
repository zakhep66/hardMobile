package com.example.currencyconverter.data_source

import com.example.currencyconverter.data.CurrencyApi
import com.example.currencyconverter.data.CurrencyResponse

class RemoteDataSource(private val currencyApi: CurrencyApi) {
    suspend fun getCurrencies(): CurrencyResponse {
        return currencyApi.getCurrencies()
    }
}