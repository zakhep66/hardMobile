package com.example.currencyconverter.data

import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyApi {
    // access_key=e54c5a4c3c81c03243a70e1f3ea53ff2&format=1 - key2

    /*@GET("/api/latest?access_key=65df790574c66c811ec378cd9837ed09&format=1")
    suspend fun  getCurrencies() : CurrencyResponse*/

    @GET("/db.json")
    suspend fun  getCurrencies() : CurrencyResponse

/*    @GET("/api/{date}?access_key=65df790574c66c811ec378cd9837ed09&format=1")
    fun getCurrencyByDate(@Path("date") date: String?) : CurrencyResponse*/

}