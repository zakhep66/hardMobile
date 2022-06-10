package com.example.currencyconverter.data

import android.content.Context
import com.example.currencyconverter.dao.CurrencyDao
import com.example.currencyconverter.dao.DatabaseApp
import com.example.currencyconverter.dao.HistoryDao
import com.example.currencyconverter.repository.LocalDataSource
import com.example.currencyconverter.repository.RemoteDataSource


object Dependencies {

    private var currencyDao: CurrencyDao? = null
    private var historyDao: HistoryDao? = null
    private lateinit var repository: Repository

    fun getRepository(context: Context): Repository {
        if (currencyDao == null || historyDao == null) {
            currencyDao = DatabaseApp.getInstance(context)?.currencyDao()
            historyDao = DatabaseApp.getInstance(context)?.historyDao()

            val localDataSource = LocalDataSource(currencyDao, historyDao)
            val remoteDataSource = RemoteDataSource(ApiBuilder.create())
            repository = Repository(localDataSource, remoteDataSource)
        }
        return repository
    }
}