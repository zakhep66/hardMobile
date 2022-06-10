package com.example.currencyconverter.data.model.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "history"
)
data class HistoryDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("currency1") var currency1: String,
    @SerializedName("currency1Val") var currency1Val: Double,
    @SerializedName("currency2") var currency2: String,
    @SerializedName("currency2Val") var currency2Val: Double,
    @SerializedName("datetime") var datetime: Long = System.currentTimeMillis(),
)