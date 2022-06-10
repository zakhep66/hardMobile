package com.example.currencyconverter.ui.converter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.data.Dependencies
import com.example.currencyconverter.databinding.ConverterFragmentBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val CURRENCY_FROM = "CURRENCY_FROM"
private const val CURRENCY_TO = "CURRENCY_TO"
private const val RATE = "RATE"

/**
 * A simple [Fragment] subclass.
 * Use the [ConverterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConverterFragment : Fragment() {
    lateinit var binding: ConverterFragmentBinding

    private var currencyFrom: String? = null
    private var currencyTo: String? = null
    private var rate: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //запись назв валют
            currencyFrom = it.getString(CURRENCY_FROM)
            currencyTo = it.getString(CURRENCY_TO)
            rate = it.getString(RATE)?.toDouble()
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = ConverterFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelFactory = ConverterViewModelFactory(Dependencies.getRepository(requireContext()))
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ConverterViewModel::class.java)

        viewModel.currencyUpdateFrom(currencyFrom)
        viewModel.currencyUpdateTo(currencyTo)
        viewModel.updateRate(rate)

        viewModel.from.observe(viewLifecycleOwner) { it.let { binding.currencyFromSwap.text = it } }
        viewModel.to.observe(viewLifecycleOwner) { it.let { binding.currencyToSwap.text = it } }
        viewModel.toVal.observe(viewLifecycleOwner) { it.let { binding.inputTo.hint = it.toString() } }
        viewModel.rate.observe(viewLifecycleOwner) { it.let { binding.inputTo.hint = it.toString() } }

        binding.inputFromEdit.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                if (s != "") {
//                    viewModel.valueUpdateTo(s.toString())
//                    viewModel.valueUpdateFrom(s.toString())
//                }
                if (s.isNotBlank()) {
                    viewModel.valueUpdateTo(s.toString())
                    viewModel.valueUpdateFrom(s.toString())
                } else {
                    viewModel.valueUpdateTo(0.toString())
                    viewModel.valueUpdateFrom(0.toString())
                }
            }

            override fun afterTextChanged(s: Editable) {
//
            }

        })

        binding.convertBtn.setOnClickListener {
            viewModel.addToHistory()
        }

    }


    companion object {

        @JvmStatic
        fun newInstance(currencyFrom: String, currencyTo: String, rate: Double) =
            ConverterFragment().apply {
                arguments = Bundle().apply {
                    putString(CURRENCY_FROM, currencyFrom)
                    putString(CURRENCY_TO, currencyTo)
                    putString(RATE, rate.toString())
                }
            }
    }
}