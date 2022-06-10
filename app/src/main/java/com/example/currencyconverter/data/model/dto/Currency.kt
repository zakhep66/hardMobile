package com.example.currencyconverter.data.model.dto

import  androidx.room.Entity
import com.google.gson.annotations.SerializedName
import androidx.room.PrimaryKey

import com.example.currencyconverter.data.model.Currencies
import com.example.currencyconverter.data.model.Currency

@Entity(
    tableName = "currency"
)
data class CurrencyDb(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @SerializedName("base") var base: String,
    @SerializedName("date") var date: String,
    @SerializedName("timestamp") var timestamp: Long,
    @SerializedName("name") var name: String,
    @SerializedName("value") var value: Double,
    @SerializedName("like") var like: Boolean
) {
    fun toCurrency(): Currency = Currency(
        name = name,
        value = value,
        like = like
    )

    companion object {
        fun fromCurrency(currency: Currency, currencies: Currencies): CurrencyDb = CurrencyDb(
            uid = 0,
            base = currencies.base,
            timestamp = System.currentTimeMillis(),
            date = currencies.date,
            name = currency.name,
            value = currency.value,
            like = currency.like

        )
    }
}
