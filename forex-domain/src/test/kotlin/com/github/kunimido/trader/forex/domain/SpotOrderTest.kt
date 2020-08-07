package com.github.kunimido.trader.forex.domain

import com.github.kunimido.trader.forex.domain.Side.BUY
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal
import java.time.LocalDate

class SpotOrderTest {

    val eur = Currency("EUR", 2)

    val jpy = Currency("JPY", 0)

    val usd = Currency("USD", 2)

    val eurJpy = CurrencyPair(eur, jpy, 2)

    @Test
    fun `constructor should pass`() {
        SpotOrder(eurJpy, BUY, Amount(eur, BigDecimal("1000")),
                LocalDate.now().plusDays(2), LocalDate.now())
    }

    @Test
    fun `constructor with currency of amount not in the traded pair should throw exception`() {
        assertThrows<IllegalArgumentException> {
            SpotOrder(eurJpy, BUY, Amount(usd, BigDecimal("1000")),
                    LocalDate.now().plusDays(2), LocalDate.now())
        }
    }

    @Test
    fun `constructor with spotDate before tradeDate should throw exception`() {
        assertThrows<IllegalArgumentException> {
            SpotOrder(eurJpy, BUY, Amount(usd, BigDecimal("1000")),
                    LocalDate.now().minusDays(1), LocalDate.now())
        }
    }

}