package com.example.currencyconverter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.currencyconverter.databinding.MainActivityBinding
import com.example.currencyconverter.ui.main.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction() // убрал эту штуку и заработа навегация и приложение в принципе
//                    .replace(R.id.container, MainFragment.newInstance())
//                    .commitNow()
//        }

            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            val navController = findNavController(R.id.fragment)

            bottomNavigationView.setupWithNavController(navController)



    }
}
