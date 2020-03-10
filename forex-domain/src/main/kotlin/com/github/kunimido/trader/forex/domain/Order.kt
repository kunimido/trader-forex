package com.github.kunimido.trader.forex.domain

import java.time.LocalDate

interface Order {
    val pair: CurrencyPair
    val side: Side
    val amount: Amount
    val tradeDate: LocalDate
}

enum class Side {
    BUY, SELL
}