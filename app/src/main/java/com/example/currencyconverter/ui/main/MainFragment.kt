package com.example.currencyconverter.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.DependencyInjection
import com.example.currencyconverter.R


class MainFragment : Fragment() { // TODO добавить ресайклер, в него будут приходить данные



    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val viewModelFactory = MainViewModelFactory(DependencyInjection.repository) // пока нет внутреннего хранилища - идёт в сеть
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.init() // получил данные
        viewModel.liveData.observe(viewLifecycleOwner){ response ->
            Log.d("MY_TAG", response.currencyList.toString())
        }
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}