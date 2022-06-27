package com.example.currencyconverter.data

import androidx.annotation.NonNull
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object ApiBuilder {
    private const val BASE_URL: String = "http://jsonserver.std-1527.ist.mospolytech.ru/"
//    private const val BASE_URL: String = "http://data.fixer.io/"
    private val client: OkHttpClient = buildClient()


    @NonNull
    private fun buildInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @NonNull
    private fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @NonNull
    private fun buildClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(buildInterceptor())
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    fun create(): CurrencyApi = buildRetrofit().create(CurrencyApi::class.java)

}