package com.github.kunimido.trader.forex.service.upstream

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager
import io.smallrye.reactive.messaging.connectors.InMemoryConnector

internal class InMemoryChannelResource : QuarkusTestResourceLifecycleManager {

    override fun start(): MutableMap<String, String> {
        return InMemoryConnector.switchIncomingChannelsToInMemory("forex-order-topic")
    }

    override fun stop() {
        InMemoryConnector.clear()
    }

}