package com.github.kunimido.trader.forex.domain

data class CurrencyPair(val baseCurrency: Currency, val quoteCurrency: Currency, val decimalPlaces: Int) {

    init {
        require(baseCurrency.code != quoteCurrency.code) { "baseCurrency and quoteCurrency cannot be the same" }
        require(decimalPlaces >= 0) { "decimalPlaces must be non negative, but was $decimalPlaces" }
    }

    val code: String by lazy { "${baseCurrency.code}/${quoteCurrency.code}" }

    fun getOther(fromCurrency: Currency): Currency = when (fromCurrency.code) {
        baseCurrency.code -> quoteCurrency
        quoteCurrency.code -> baseCurrency
        else -> throw IllegalArgumentException("Currency pair $code does not contain currency ${fromCurrency.code}")
    }

    fun contains(currency: Currency): Boolean = (currency == baseCurrency || currency == quoteCurrency)

}