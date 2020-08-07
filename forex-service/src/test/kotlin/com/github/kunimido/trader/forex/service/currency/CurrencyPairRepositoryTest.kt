package com.github.kunimido.trader.forex.service.currency

import io.quarkus.test.junit.QuarkusTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import javax.inject.Inject

@QuarkusTest
@Tag("database")
class CurrencyPairRepositoryTest {

    @Inject
    lateinit var repository: CurrencyPairRepository

    @Test
    fun `should return null when findByCode with AAA_BBB`() {
        assertNull(repository.findByCode("AAA/BBB"))
    }

    @Test
    fun `should return CurrencyPair when findByCode with EUR_JPY`() {
        val pair = repository.findByCode("EUR/JPY")
        assertNotNull(pair)
        assertEquals("EUR", pair!!.baseCurrency.code)
        assertEquals("JPY", pair.quoteCurrency.code)
    }

}