package com.github.kunimido.trader.forex.service.upstream

import com.github.kunimido.trader.forex.domain.Currency
import com.github.kunimido.trader.forex.domain.CurrencyPair
import com.github.kunimido.trader.forex.service.currency.CurrencyPairService
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
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

    val currencyPairService = mockk<CurrencyPairService>()

    lateinit var orderMapper: OrderMapper

    @BeforeEach
    fun beforeEach() {
        installMockForType(currencyPairService, CurrencyPairService::class.java)
        orderMapper = spyk(OrderMapper(currencyPairService))
        installMockForType(orderMapper, OrderMapper::class.java)
    }

    @Test
    fun `should receive the message`() {
        every {
            currencyPairService.findByCode(eq("EUR/JPY"))
        } returns CurrencyPair(Currency("EUR", 2), Currency("JPY", 0), 2)

        connector.source<OrderDto>("forex-order-topic")
                .send(OrderDto(InstrumentDto("FXSPOT", "EUR/JPY")))

        coVerify(timeout = 2000L) {
            orderMapper.map(capture(slot()))
        }
    }

}