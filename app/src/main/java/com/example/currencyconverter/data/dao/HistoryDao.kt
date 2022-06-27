package com.example.currencyconverter.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverter.data.model.dto.HistoryDb

@Dao
interface HistoryDao {
    @Query("SELECT * FROM history ORDER BY datetime DESC")
    fun getHistory(): List<HistoryDb>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(historyItem: HistoryDb)

    @Query("SELECT * FROM history WHERE datetime BETWEEN :dateFrom AND :dateTo ORDER BY datetime DESC")
    fun getDateFilteredHistory(dateFrom: Long, dateTo: Long): List<HistoryDb>

    @Query("DELETE FROM history")
    suspend fun deleteAll()
}