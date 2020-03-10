package com.github.kunimido.trader.forex.domain

import java.util.regex.Pattern

private val iso4217Pattern = Pattern.compile("[A-Z]{3}")

data class Currency(val code: String, val decimalPlaces: Int) {

    init {
        require(iso4217Pattern.matcher(code).matches()) { "code must be 3 uppercase letters but was $code" }
        require(decimalPlaces >= 0) { "decimalPlaces must be non negative, but was $decimalPlaces" }
    }

}