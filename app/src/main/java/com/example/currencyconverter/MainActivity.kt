package com.example.currencyconverter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.databinding.MainActivityBinding
import com.example.currencyconverter.ui.main.MainFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GlobalScope.launch(Dispatchers.IO){ // io Это второстепенный поток. Выполнится асинхронно

            try {
                val currencies = DependencyInjection.repository.getCurrencies()
                Log.d("MY_TAG", "$currencies")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("MY_TAG", e.localizedMessage)
            }

//            val currencies = DependencyInjection.repository.getCurrencies() // этот корутин нужно запускать из вьюшки модели
//            Log.d("MY_TAG", "$currencies")
//            Log.d("MY_TAG is success", "${currencies?.rates}")
        }

    }
}
