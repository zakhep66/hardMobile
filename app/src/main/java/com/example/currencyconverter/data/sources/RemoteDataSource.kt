package com.example.currencyconverter.data.sources

import androidx.annotation.NonNull
import com.example.currencyconverter.data.CurrencyApi
import com.example.currencyconverter.data.CurrencyResponse
import java.lang.Exception

class RemoteDataSource(private val api: CurrencyApi) {

    @NonNull
    suspend fun loadCurrencies(): CurrencyResponse {
        return try {
            api.getCurrencies()
        } catch (error: Exception) {
            throw Exception("нет доступа к fixer.io")
        }
    }
}