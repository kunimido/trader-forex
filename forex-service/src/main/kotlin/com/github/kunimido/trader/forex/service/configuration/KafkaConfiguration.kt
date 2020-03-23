package com.github.kunimido.trader.forex.service.configuration

import com.github.kunimido.trader.forex.core.OrderProcessor
import com.github.kunimido.trader.forex.service.dto.OrderDto
import com.github.kunimido.trader.forex.service.mapping.OrderMapper
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.KStream
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.support.serializer.JsonSerde

@Configuration(proxyBeanMethods = false)
@EnableKafkaStreams
class KafkaConfiguration {

    @Bean
    fun orderStream(builder: StreamsBuilder, processor: OrderProcessor, mapper: OrderMapper): KStream<String, OrderDto> {
        val result = builder.stream(
                "forex-order-topic",
                Consumed.with(Serdes.String(), JsonSerde(OrderDto::class.java))
        )
        result.foreach { _, v ->
            processor.process(mapper.mapToOrder(v))
        }
        return result
    }

}