package com.example.currencyconverter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import java.util.*

@Dao

interface CurrencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addNode(currency: Currency)

}