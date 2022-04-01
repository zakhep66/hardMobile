package com.example.currencyconverter

import com.example.currencyconverter.data.CurrencyApi
import com.example.currencyconverter.data_source.LocalDataSource
import com.example.currencyconverter.data_source.RemoteDataSource
import com.example.currencyconverter.domain.repository.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DependencyInjection {

    private val interceptor = HttpLoggingInterceptor().also {
        it.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder() // здесь указываем базовый URL и добавляем Gson конвертер
        .client(client)
        .baseUrl("http://data.fixer.io/") // базовый URL
        .addConverterFactory(GsonConverterFactory.create()) // добавление конвертера
        .build()

    private val service: CurrencyApi = retrofit.create(CurrencyApi::class.java) // объект, содержащий базовый URL и способность преобразовать json данные с помощью Gson. Его передаём в интерфейс

    private val localDataSource = LocalDataSource()
    private val remoteDataSource = RemoteDataSource(service)


    val repository = Repository(localDataSource, remoteDataSource)

}