package com.example.currencyconverter.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.Dependencies
import com.example.currencyconverter.databinding.MainFragmentBinding
import com.example.currencyconverter.ui.converter.ConverterFragment
import com.example.currencyconverter.ui.history.HistoryFragment


interface MainAdapterListener {
    fun itemClick(name: String, rate: Double)
    fun likeClick(position: Int)
}

class MainFragment : Fragment(), MainAdapterListener {
    lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }
// for future
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyBtn.setOnClickListener {
            val historyFragment = fragmentManager?.beginTransaction()
            historyFragment?.replace(R.id.container, HistoryFragment())
            historyFragment?.addToBackStack(null)
            historyFragment?.commit()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val recyclerView: RecyclerView = binding.currencyList
        val adapter = MainAdapter(this, this)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val viewModelFactory =
            ViewModelFactory(Dependencies.getRepository(requireContext()))
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(MainViewModel::class.java)


        viewModel.currenciesData.observe(viewLifecycleOwner) {
            it.let { adapter.updateCurrencies(it.rates)
                recyclerView.adapter = adapter
            }
        }
    }

    override fun itemClick(name: String, rate: Double) {
        val exchangeFragment = fragmentManager?.beginTransaction()
        exchangeFragment?.replace(R.id.container, ConverterFragment.newInstance("EUR", name, rate))
        exchangeFragment?.addToBackStack(null)
        exchangeFragment?.commit()
    }

    override fun likeClick(position: Int) {
        val newCurrenciesData = viewModel.currenciesData.value
        newCurrenciesData!!.rates[position].like = !newCurrenciesData.rates[position].like
        newCurrenciesData.rates = viewModel.currenciesData.value!!.rates.sortedBy { !it.like }
        viewModel.currenciesData.postValue(newCurrenciesData)
    }
}