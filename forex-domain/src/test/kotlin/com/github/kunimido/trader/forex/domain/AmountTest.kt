package com.github.kunimido.trader.forex.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

private val EUR = Currency("EUR", 2)
private val JPY = Currency("JPY", 0)

internal class AmountTest {

    @Test
    fun `constructor with currency=EUR and quantity=1,00 should pass`() {
        assertEquals(EUR.decimalPlaces, Amount(EUR, BigDecimal("1.00")).quantity.scale())
    }

    @Test
    fun `constructor with currency=EUR and quantity=1,0 should automatically add decimal places`() {
        assertEquals(EUR.decimalPlaces, Amount(EUR, BigDecimal("1.0")).quantity.scale())
    }

    @Test
    fun `constructor with currency=EUR and quantity=1,015 should automatically round to 1,02`() {
        assertEquals(BigDecimal("1.02"), Amount(EUR, BigDecimal("1.015")).quantity)
    }

    @Test
    fun `add 2,01 EUR to 1,05 EUR should give 3,06 EUR`() {
        val total = Amount(EUR, BigDecimal("1.05")) + Amount(EUR, BigDecimal("2.01"))
        assertAll(
                { assertEquals(EUR, total.currency) },
                { assertEquals(BigDecimal("3.06"), total.quantity) }
        )
    }

    @Test
    fun `add 100 JPY to 1,00 EUR should throw exception`() {
        assertThrows<IllegalArgumentException> {
            Amount(EUR, BigDecimal("1.00")) + Amount(JPY, BigDecimal("100"))
        }
    }

    @Test
    fun `subtract 2,01 EUR from 1,05 EUR should give -0,96 EUR`() {
        val total = Amount(EUR, BigDecimal("1.05")) - Amount(EUR, BigDecimal("2.01"))
        assertAll(
                { assertEquals(EUR, total.currency) },
                { assertEquals(BigDecimal("-0.96"), total.quantity) }
        )
    }

    @Test
    fun `subtract 100 JPY from 1,00 EUR should throw exception`() {
        assertThrows<IllegalArgumentException> {
            Amount(EUR, BigDecimal("1.00")) - Amount(JPY, BigDecimal("100"))
        }
    }

    @Test
    fun `1,00 EUR lower than 2,00 EUR should be true`() {
        assertTrue(Amount(EUR, BigDecimal("1.00")) < Amount(EUR, BigDecimal("2.00")))
    }

    @Test
    fun `1,00 EUR lower than 100 JPY should throw exception`() {
        assertThrows<IllegalArgumentException> {
            Amount(EUR, BigDecimal("1.00")) < Amount(JPY, BigDecimal("100"))
        }
    }

    @Test
    fun `equals between 1,00 EUR and 1,00 EUR should return true`() {
        assertEquals(Amount(EUR, BigDecimal("1.00")), Amount(EUR, BigDecimal("1.00")))
    }

    @Test
    fun `equals between 1,00 EUR and 1,01 EUR should return false`() {
        assertNotEquals(Amount(EUR, BigDecimal("1.00")), Amount(EUR, BigDecimal("1.01")))
    }

    @Test
    fun `equals between 100 EUR and 100 JPY should return false`() {
        assertNotEquals(Amount(EUR, BigDecimal("100")), Amount(JPY, BigDecimal("100")))
    }

}