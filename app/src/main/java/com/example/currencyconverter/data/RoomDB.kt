package com.example.currencyconverter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencyconverter.domain.model.Currency


@Database(entities = [Currency::class], version = 1, exportSchema = false)
abstract class RoomDB : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyDao

    companion object {
        @Volatile
        var database: RoomDB? = null

        fun getInstance(context: Context): RoomDB {
            return if (database === null) {
                database = Room.databaseBuilder(
                    context, RoomDB::class.java, "currency_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                database as RoomDB
            }else{
                database as RoomDB
            }
        }

    }
}