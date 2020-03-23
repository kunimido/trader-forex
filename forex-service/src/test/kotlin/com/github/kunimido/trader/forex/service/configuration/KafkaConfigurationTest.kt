package com.github.kunimido.trader.forex.service.configuration

import com.github.kunimido.trader.forex.core.OrderProcessor
import io.mockk.coVerify
import io.mockk.slot
import io.mockk.spyk
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.messaging.support.MessageBuilder

@SpringBootTest(webEnvironment = NONE, properties = ["spring.main.allow-bean-definition-overriding=true"])
@EmbeddedKafka(
        topics = ["forex-order-topic"],
        bootstrapServersProperty = "spring.kafka.bootstrap-servers",
        brokerProperties = ["log.dir=build/tmp/embedded-kafka"]
)
internal class KafkaConfigurationTest(
        @Autowired private val kafkaTemplate: KafkaTemplate<String, Any>,
        @Autowired private val orderProcessor: OrderProcessor
) {

    @TestConfiguration
    class ForexCoreTestConfiguration {

        @Bean
        @Primary
        fun orderProcessor(): OrderProcessor {
            return spyk(OrderProcessor())
        }

    }

    @Test
    fun `orderProcessor should receive the message`() {
        kafkaTemplate.defaultTopic = "forex-order-topic"
        kafkaTemplate.send(
                MessageBuilder.withPayload("""
                        {
                            "instrument": {
                                "securityType": "FXSPOT",
                                "securityId": "EUR/JPY"
                            }
                        }
                        """.trimIndent()
                ).build()
        )
        coVerify(timeout = 2000L) {
            orderProcessor.process(capture(slot()))
        }
    }

}