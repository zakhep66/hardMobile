package com.example.currencyconverter.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverter.data.model.dto.HistoryDb

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history")
    fun getHistory(): List<HistoryDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(historyItem: HistoryDb)

    @Query("DELETE FROM history")
    suspend fun deleteAll()
}