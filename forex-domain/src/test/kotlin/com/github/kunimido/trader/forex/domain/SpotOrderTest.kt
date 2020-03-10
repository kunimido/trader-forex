package com.github.kunimido.trader.forex.domain

import com.github.kunimido.trader.forex.domain.Side.BUY
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.LocalDate

private val EUR = Currency("EUR", 2)
private val JPY = Currency("JPY", 0)
private val USD = Currency("USD", 2)
private val EUR_JPY = CurrencyPair(EUR, JPY, 2)

internal class SpotOrderTest {

    @Test
    fun `constructor should pass`() {
        SpotOrder(
                EUR_JPY,
                BUY,
                Amount(EUR, BigDecimal("1000")),
                LocalDate.now().plusDays(2),
                LocalDate.now()
        )
    }

    @Test
    fun `constructor with currency of amount not in the traded pair should throw exception`() {
        assertThrows<IllegalArgumentException> {
            SpotOrder(
                    EUR_JPY,
                    BUY,
                    Amount(USD, BigDecimal("1000")),
                    LocalDate.now().plusDays(2),
                    LocalDate.now()
            )
        }
    }

    @Test
    fun `constructor with spotDate before tradeDate should throw exception`() {
        assertThrows<IllegalArgumentException> {
            SpotOrder(
                    EUR_JPY,
                    BUY,
                    Amount(USD, BigDecimal("1000")),
                    LocalDate.now().minusDays(1),
                    LocalDate.now()
            )
        }
    }

}