package com.example.currencyconverter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.DependencyInjection
import com.example.currencyconverter.R


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val viewModelFactory = MainViewModelFactory(DependencyInjection.repository) // пока нет внутреннего хранилища - идёт в сеть
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}