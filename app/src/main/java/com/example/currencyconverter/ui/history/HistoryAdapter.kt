package com.example.currencyconverter.ui.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.model.dto.HistoryDb
import java.text.SimpleDateFormat
import java.util.*


class HistoryAdapter(): RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    private var dates: MutableList<String> = mutableListOf()
    private var currencies: MutableList<String> = mutableListOf()
    private var currenciesFrom: MutableList<String> = mutableListOf()
    private var currenciesTo: MutableList<String> = mutableListOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val historyTo: TextView = view.findViewById(R.id.history_to)
        val date: TextView = view.findViewById(R.id.date)
        val historyFromValue: TextView = view.findViewById(R.id.historyFromValue)
        val historyToValue: TextView = view.findViewById(R.id.historyToValue)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.history_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.historyTo.text = currencies[position]
        viewHolder.date.text = dates[position]
        viewHolder.historyFromValue.text = currenciesFrom[position]
        viewHolder.historyToValue.text = currenciesTo[position]
    }

    override fun getItemCount() = currencies.size


    fun updateHistory(history: List<HistoryDb>) {
        currencies = history.map { it.currency2 }.toMutableList()
        currenciesFrom = history.map { it.currency1Val.toString() }.toMutableList()
        currenciesTo = history.map { it.currency2Val.toString() }.toMutableList()
        dates = history.map {
            SimpleDateFormat("dd.MM.yyyy").format(Date(it.datetime))
        }.toMutableList()
    }


}