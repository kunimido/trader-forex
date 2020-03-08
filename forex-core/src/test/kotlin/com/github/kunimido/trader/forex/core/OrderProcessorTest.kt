package com.github.kunimido.trader.forex.core

import com.github.kunimido.trader.forex.domain.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

internal class OrderProcessorTest {

    private val orderProcessor: OrderProcessor = OrderProcessor()

    @Test
    fun `OrderProcessor is not implemented yet`() {
        assertThrows<NotImplementedError> {
            orderProcessor.process(Order(LocalDate.now()))
        }
    }

}