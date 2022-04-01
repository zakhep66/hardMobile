package com.example.currencyconverter.ui.mapper

import com.example.currencyconverter.domain.model.Currencies
import com.example.currencyconverter.ui.model.CurrenciesUiModel

object CurrencyUiModelMapper {
    fun mapDomainModelToUiModel(currencies: Currencies): CurrenciesUiModel {
        return CurrenciesUiModel(
            currencyList = currencies.rates,
            isLoading = true,
        )
    }
}