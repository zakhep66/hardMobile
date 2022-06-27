package com.example.currencyconverter.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.currencyconverter.data.model.dto.CurrencyDb
import com.example.currencyconverter.data.model.dto.HistoryDb


@Database(entities = [CurrencyDb::class, HistoryDb::class], version = 1)
abstract class DatabaseApp : RoomDatabase()  {
    abstract fun currencyDao(): CurrencyDao
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        var database: DatabaseApp? = null

        fun getInstance(context: Context): DatabaseApp? {
            if (database == null) {
                synchronized(this) {
                    val myDataBase = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseApp::class.java,
                        "currency converter"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    database = myDataBase
                    return myDataBase
                }
            }
            return database
        }
    }
}