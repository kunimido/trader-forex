package com.github.kunimido.trader.forex.service.upstream

import com.github.kunimido.trader.forex.core.OrderProcessor
import io.smallrye.reactive.messaging.annotations.Blocking
import org.eclipse.microprofile.reactive.messaging.Incoming
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional

@ApplicationScoped
class UpstreamOrderReceiver(private val mapper: OrderMapper) {

    private val orderProcessor = OrderProcessor()

    @Incoming("forex-order-topic")
    @Blocking
    @Transactional
    fun receive(upstreamOrder: OrderDto) {
        orderProcessor.process(mapper.map(upstreamOrder))
    }

}