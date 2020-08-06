package com.github.kunimido.trader.forex.service.upstream

import io.mockk.coVerify
import io.mockk.slot
import io.mockk.spyk
import io.quarkus.test.common.QuarkusTestResource
import io.quarkus.test.junit.QuarkusMock.installMockForType
import io.quarkus.test.junit.QuarkusTest
import io.smallrye.reactive.messaging.connectors.InMemoryConnector
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.enterprise.inject.Any
import javax.inject.Inject

@QuarkusTest
@QuarkusTestResource(InMemoryChannelResource::class)
internal class UpstreamOrderReceiverTest {

    @Inject
    @field: Any
    lateinit var connector: InMemoryConnector

    lateinit var orderMapper: OrderMapper

    @BeforeEach
    fun beforeEach() {
        orderMapper = spyk(OrderMapper())
        installMockForType(orderMapper, OrderMapper::class.java)
    }

    @Test
    fun `orderProcessor should receive the message`() {
        connector.source<OrderDto>("forex-order-topic")
                .send(OrderDto(InstrumentDto("FXSPOT", "EUR/JPY")))
        coVerify(timeout = 2000L) {
            orderMapper.map(capture(slot()))
        }
    }

}