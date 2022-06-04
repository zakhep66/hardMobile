package com.example.currencyconverter.data

import androidx.room.*
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import java.util.*

@Dao
interface CurrencyDao {
//    @Query("SELECT * FROM currencies")
//    fun getRoomCurrencyList(): LiveData<List<Currency>>

    @Query("SELECT * FROM currencies")
    suspend fun getRoomCurrencyList(): MutableList<Currency>

    @Query("SELECT * FROM currencies WHERE isFavorite = 1")
    suspend fun getFavoriteCurrencyList(): MutableList<Currency>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCurrencyList(currency: com.example.currencyconverter.domain.model.Currency)

    @Query("UPDATE currencies SET isFavorite = :isFavorite WHERE name = :name")
    suspend fun updateListFavoriteCurrency(name: String, isFavorite: Boolean)

    @Query("UPDATE currencies SET value = :value WHERE name = :name")
    suspend fun updateListCurrency(name: String, value: Double)
}