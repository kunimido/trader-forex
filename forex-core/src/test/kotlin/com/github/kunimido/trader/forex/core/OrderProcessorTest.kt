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

private val EUR = Currency("EUR", 2)
private val JPY = Currency("JPY", 0)
private val EUR_JPY = CurrencyPair(EUR, JPY, 2)

internal class OrderProcessorTest {

    private val orderProcessor: OrderProcessor = OrderProcessor()

    @Test
    fun `OrderProcessor is not implemented yet`() {
        assertThrows<NotImplementedError> {
            orderProcessor.process(
                    SpotOrder(
                            EUR_JPY,
                            SELL,
                            Amount(EUR, BigDecimal("1000")),
                            LocalDate.now().plusDays(1),
                            LocalDate.now()
                    )
            )
        }
    }

}