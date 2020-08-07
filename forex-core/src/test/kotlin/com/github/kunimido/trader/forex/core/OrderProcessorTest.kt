package com.github.kunimido.trader.forex.core

import com.github.kunimido.trader.forex.domain.Amount
import com.github.kunimido.trader.forex.domain.Currency
import com.github.kunimido.trader.forex.domain.CurrencyPair
import com.github.kunimido.trader.forex.domain.Side.SELL
import com.github.kunimido.trader.forex.domain.SpotOrder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.LocalDate

class OrderProcessorTest {

    val eur = Currency("EUR", 2)

    val jpy = Currency("JPY", 0)

    val eurJpy = CurrencyPair(eur, jpy, 2)

    val orderProcessor: OrderProcessor = OrderProcessor()

    @Test
    fun `OrderProcessor is not implemented yet`() {
        assertThrows<NotImplementedError> {
            orderProcessor.process(SpotOrder(eurJpy, SELL, Amount(eur, BigDecimal("1000")),
                    LocalDate.now().plusDays(1), LocalDate.now()))
        }
    }

}