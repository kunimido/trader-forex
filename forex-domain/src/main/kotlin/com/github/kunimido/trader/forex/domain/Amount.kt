package com.github.kunimido.trader.forex.domain

import java.math.BigDecimal
import java.math.RoundingMode.HALF_EVEN

class Amount(val currency: Currency, _quantity: BigDecimal) : Comparable<Amount> {

    val quantity: BigDecimal = _quantity.setScale(currency.decimalPlaces, HALF_EVEN)

    operator fun plus(other: Amount): Amount {
        requireSameCurrency(other)
        return Amount(currency, quantity + other.quantity)
    }

    operator fun minus(other: Amount): Amount {
        requireSameCurrency(other)
        return Amount(currency, quantity - other.quantity)
    }

    override fun compareTo(other: Amount): Int {
        requireSameCurrency(other)
        return quantity.compareTo(other.quantity)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Amount
        if (currency != other.currency) return false
        if (quantity.compareTo(other.quantity) != 0) return false

        return true
    }

    override fun hashCode(): Int {
        var result = currency.hashCode()
        result = 31 * result + quantity.hashCode()
        return result
    }

    private fun requireSameCurrency(other: Amount) = require(currency == other.currency) {
        "Amounts must have the same currency: ${other.currency} differs from $currency"
    }

}