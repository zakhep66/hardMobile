package com.example.currencyconverter.domain.repository

import com.example.currencyconverter.data_source.LocalDataSource
import com.example.currencyconverter.data_source.RemoteDataSource
import com.example.currencyconverter.domain.model.Currencies
import com.example.currencyconverter.domain.mapper.CurrencyDtoMapper
import java.lang.Exception

class Repository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun getCurrencies(): Currencies? {
        return try{
            val response = remoteDataSource.getCurrencies()
            CurrencyDtoMapper.mapResponseToDomainModel(response)
        } catch (e: Exception){
            null
        }
    }
}