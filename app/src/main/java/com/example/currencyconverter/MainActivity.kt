package com.example.currencyconverter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        GlobalScope.launch(Dispatchers.IO){ // io Это второстепенный поток. Выполнится асинхронно
            val currencies = DependencyInjection.repository.getCurrencies() // этот корутин нужно запускать из вьюшки модели
            Log.d("MY_TAG", "$currencies")
            Log.d("MY_TAG is success", "${currencies?.rates}")
        }
    }
}
