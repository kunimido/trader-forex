package com.github.kunimido.trader.forex.domain

import java.time.LocalDate

data class SpotOrder(
        override val pair: CurrencyPair,
        override val side: Side,
        override val amount: Amount,
        val spotDate: LocalDate,
        override val tradeDate: LocalDate
) : Order {

    init {
        require(pair.contains(amount.currency)) {
            "amount currency should be one of ${pair.code}, but was ${amount.currency}"
        }
        require(spotDate >= tradeDate) { "spot date must be after trade date" }
    }

}