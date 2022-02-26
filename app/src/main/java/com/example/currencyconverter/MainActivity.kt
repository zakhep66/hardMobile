package com.example.currencyconverter

import android.os.Bundle
import android.util.Log
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
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder() // здесь указываем базовый URL и добавляем Gson конвертер
            .client(client)
            .baseUrl("http://data.fixer.io/") // базовый URL
            .addConverterFactory(GsonConverterFactory.create()) // добавление конвертера
            .build()

        val service: CurrencyApi = retrofit.create(CurrencyApi::class.java) // объект, содержащий базовый URL и способность преобразовать json данные с помощью Gson. Его передаём в интерфейс

        GlobalScope.launch(Dispatchers.IO){ // io Это второстепенный поток. Выполнится асинхронно
            val currencies = service.getCurrencies() // этот корутин нужно запускать из вьюшки модели
            Log.d("MY_TAG", "$currencies")
        }
    }
}
