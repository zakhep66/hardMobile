package com.example.currencyconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.data.CurrencyApi
import com.example.currencyconverter.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("http://data.fixer.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CurrencyApi::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val currencies = service.getCurrencies()
            print(currencies)
        }
    }
}