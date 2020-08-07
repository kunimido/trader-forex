package com.github.kunimido.trader.forex.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CurrencyTest {

    @Test
    fun `constructor with code="JPY" and decimalPlaces=0 should pass`() {
        Currency("JPY", 0)
    }

    @Test
    fun `constructor with code="EUR" and decimalPlaces=2 should pass`() {
        Currency("EUR", 2)
    }

    @Test
    fun `constructor with code="" should throw exception`() {
        assertThrows<IllegalArgumentException> { Currency("", 2) }
    }

    @Test
    fun `constructor with code less than 3 characters should throw exception`() {
        assertThrows<IllegalArgumentException> { Currency("EU", 2) }
    }

    @Test
    fun `constructor with code more than 3 characters should throw exception`() {
        assertThrows<IllegalArgumentException> { Currency("EURO", 2) }
    }

    @Test
    fun `constructor with code="Eur" should throw exception`() {
        assertThrows<IllegalArgumentException> { Currency("Eur", 2) }
    }

    @Test
    fun `constructor with code="EU1" should throw exception`() {
        assertThrows<IllegalArgumentException> { Currency("EU1", 2) }
    }

    @Test
    fun `constructor with code="EUR" and decimalPlaces lesser than 0 should throw exception`() {
        assertThrows<IllegalArgumentException> { Currency("EUR", -1) }
    }

}