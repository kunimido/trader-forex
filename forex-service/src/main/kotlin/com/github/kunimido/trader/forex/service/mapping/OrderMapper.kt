package com.github.kunimido.trader.forex.service.mapping

import com.github.kunimido.trader.forex.domain.Amount
import com.github.kunimido.trader.forex.domain.Currency
import com.github.kunimido.trader.forex.domain.CurrencyPair
import com.github.kunimido.trader.forex.domain.Order
import com.github.kunimido.trader.forex.domain.Side.SELL
import com.github.kunimido.trader.forex.domain.SpotOrder
import com.github.kunimido.trader.forex.service.dto.OrderDto
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate

@Service
class OrderMapper {

    fun mapToOrder(source: OrderDto): Order {
        return when (source.instrument.securityType) {
            "FXSPOT" -> mapToSpotOrder(source)
            else -> throw IllegalArgumentException("Unsupported order type: ${source.instrument.securityType}")
        }
    }

    fun mapToSpotOrder(source: OrderDto): SpotOrder {
        require(source.instrument.securityId == "EUR/JPY")
        val eur = Currency("EUR", 2)
        val jpy = Currency("JPY", 0)
        return SpotOrder(
                CurrencyPair(eur, jpy, 2),
                SELL,
                Amount(eur, BigDecimal("1000")),
                LocalDate.now().plusDays(1),
                LocalDate.now()
        )
    }

}