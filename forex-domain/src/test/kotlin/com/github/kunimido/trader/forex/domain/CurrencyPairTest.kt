package com.github.kunimido.trader.forex.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CurrencyPairTest {

    val eur = Currency("EUR", 2)

    val jpy = Currency("JPY", 0)

    val usd = Currency("USD", 2)

    @Test
    fun `constructor with base=EUR, quote=JPY and decimalPlaces=4 should pass`() {
        assertEquals("EUR/JPY", CurrencyPair(eur, jpy, 4).code)
    }

    @Test
    fun `constructor with base=quote should throw exception`() {
        assertThrows<IllegalArgumentException> { CurrencyPair(eur, eur, 4) }
    }

    @Test
    fun `getOther(EUR) on EURJPY should return JPY`() {
        assertEquals(jpy, CurrencyPair(eur, jpy, 4).getOther(eur))
    }

    @Test
    fun `getOther(JPY) on EURJPY should return EUR`() {
        assertEquals(eur, CurrencyPair(eur, jpy, 4).getOther(jpy))
    }


    @Test
    fun `getOther(not currency of pair) should throw exception`() {
        assertThrows<IllegalArgumentException> { CurrencyPair(eur, jpy, 4).getOther(usd) }
    }

}