package com.example.currencyconverter.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.data.Dependencies
import com.example.currencyconverter.databinding.HistoryFragmentBinding

class HistoryFragment : Fragment() {
    lateinit var binding: HistoryFragmentBinding
    private lateinit var viewModel: HistoryViewModel

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoryFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView: RecyclerView = binding.historyList
        val adapter = HistoryAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)


        val viewModelFactory =
            HistoryViewModelFactory(Dependencies.getRepository(requireContext()))
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(HistoryViewModel::class.java)

        viewModel.historyList.observe(viewLifecycleOwner) {
            it.let {
                adapter.updateHistory(it)
                recyclerView.adapter = adapter
            }
        }
    }

}