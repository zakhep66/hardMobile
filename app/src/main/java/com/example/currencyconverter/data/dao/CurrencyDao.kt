package com.example.currencyconverter.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverter.data.model.dto.CurrencyDb

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM currency")
    suspend fun getCurrencies(): List<CurrencyDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(currency: CurrencyDb)

    @Query("DELETE FROM currency")
    suspend fun deleteAll()

    @Query("SELECT * FROM currency WHERE base = :base")
    suspend fun getCurrency(base: String?): CurrencyDb?
}