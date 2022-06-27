package com.example.currencyconverter.ui.history

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.Dependencies
import com.example.currencyconverter.databinding.HistoryFragmentBinding
import java.time.LocalDateTime

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

    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView: RecyclerView = binding.historyList
        val adapter = HistoryAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        binding.allTime.setBackgroundResource(R.color.black) // при первом запуске будет отображаться история за всё время


        val viewModelFactory =
            HistoryViewModelFactory(Dependencies.getRepository(requireContext()))
        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[HistoryViewModel::class.java]

        viewModel.getHistory()

        viewModel.historyList.observe(viewLifecycleOwner) {
            it.let {
                adapter.updateHistory(it)

                adapter.notifyDataSetChanged()
            }
        }

        binding.toastMe.setOnClickListener {
            viewModel.getDateFilteredHistory(
                LocalDateTime.now().minusDays(7), LocalDateTime.now()
            )
            binding.toastMe.setBackgroundResource(R.color.black)
            binding.month.setBackgroundResource(R.color.purple_500)
            binding.allTime.setBackgroundResource(R.color.purple_500)
        }

        binding.month.setOnClickListener {
            viewModel.getDateFilteredHistory(
                LocalDateTime.now().minusMonths(1), LocalDateTime.now()
            )
            binding.toastMe.setBackgroundResource(R.color.purple_500)
            binding.month.setBackgroundResource(R.color.black)
            binding.allTime.setBackgroundResource(R.color.purple_500)
        }

        binding.allTime.setOnClickListener {
            viewModel.getHistory()
            binding.toastMe.setBackgroundResource(R.color.purple_500)
            binding.month.setBackgroundResource(R.color.purple_500)
            binding.allTime.setBackgroundResource(R.color.black)
        }

    }
}