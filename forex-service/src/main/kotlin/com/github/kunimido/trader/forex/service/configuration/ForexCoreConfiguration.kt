package com.github.kunimido.trader.forex.service.configuration

import com.github.kunimido.trader.forex.core.OrderProcessor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ForexCoreConfiguration {

    @Bean
    fun orderProcessor(): OrderProcessor {
        return OrderProcessor()
    }

}