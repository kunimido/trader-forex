package com.github.kunimido.trader.forex.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

private val EUR = Currency("EUR", 2)
private val JPY = Currency("JPY", 0)
private val USD = Currency("USD", 2)

internal class CurrencyPairTest {

    @Test
    fun `constructor with base=EUR, quote=JPY and decimalPlaces=4 should pass`() {
        assertEquals("EUR/JPY", CurrencyPair(EUR, JPY, 4).code)
    }

    @Test
    fun `constructor with base=quote should throw exception`() {
        assertThrows<IllegalArgumentException> { CurrencyPair(EUR, EUR, 4) }
    }

    @Test
    fun `getOther(EUR) on EURJPY should return JPY`() {
        assertEquals(JPY, CurrencyPair(EUR, JPY, 4).getOther(EUR))
    }

    @Test
    fun `getOther(JPY) on EURJPY should return EUR`() {
        assertEquals(EUR, CurrencyPair(EUR, JPY, 4).getOther(JPY))
    }


    @Test
    fun `getOther(not currency of pair) should throw exception`() {
        assertThrows<IllegalArgumentException> { CurrencyPair(EUR, JPY, 4).getOther(USD) }
    }

}