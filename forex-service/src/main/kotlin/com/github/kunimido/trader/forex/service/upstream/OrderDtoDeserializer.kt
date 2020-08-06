package com.github.kunimido.trader.forex.service.upstream

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer

class OrderDtoDeserializer : ObjectMapperDeserializer<OrderDto>(OrderDto::class.java)