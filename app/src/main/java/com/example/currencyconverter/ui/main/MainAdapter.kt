package com.example.currencyconverter.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.data.model.Currency

class MainAdapter(
    var listener: MainAdapterListener, var context: MainFragment): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var currencies: MutableList<Currency> = mutableListOf()


//    private var valutes: MutableList<String> = mutableListOf()

//binding
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val currencyTo: TextView = view.findViewById(R.id.currency_to)
//        val currencyItem: ConstraintLayout = view.findViewById(R.id.currency_item)

        private var currency: Currency? = null


        private val currencyTo: TextView = view.findViewById(R.id.currency_to)
        val currencyItem: ConstraintLayout = view.findViewById(R.id.currency_item)
        val like: ImageButton = view.findViewById(R.id.like)

        fun bind(currency: Currency) {
            this.currency = currency
            currencyTo.text = currency.name
            if (currency.like) { like.setImageResource(R.drawable.ic_delete_24) }
            else { like.setImageResource(R.drawable.ic_add_24) }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.currency_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(currencies[position])

        viewHolder.currencyItem.setOnClickListener {
            listener.itemClick(currencies[position].name, currencies[position].value)
        }
        viewHolder.like.setOnClickListener {
            listener.likeClick(position)
        }
    }

    override fun getItemCount() = currencies.size

    fun updateCurrencies(list: List<Currency>) {
        currencies = list.toMutableList()
//        valutes = map.keys.toMutableList()
    }

}
