package com.example.currencyconverter.data

import retrofit2.http.GET

interface CurrencyApi {

    @GET("api/latest?access_key=97300f1a151ff9cfc0f0add35437325f")
    suspend fun getCurrencies() : CurrencyResponse
}