package com.github.kunimido.trader.forex.service.upstream

import com.github.kunimido.trader.forex.domain.Amount
import com.github.kunimido.trader.forex.domain.Currency
import com.github.kunimido.trader.forex.domain.CurrencyPair
import com.github.kunimido.trader.forex.domain.Order
import com.github.kunimido.trader.forex.domain.Side.SELL
import com.github.kunimido.trader.forex.domain.SpotOrder
import java.math.BigDecimal
import java.time.LocalDate
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class OrderMapper {

    fun map(source: OrderDto): Order {
        return when (source.instrument.securityType) {
            "FXSPOT" -> mapToSpotOrder(source)
            else -> throw IllegalArgumentException("Unsupported order type: ${source.instrument.securityType}")
        }
    }

    private fun mapToSpotOrder(source: OrderDto): SpotOrder {
        require(source.instrument.securityId == "EUR/JPY")
        val pair = CurrencyPair(Currency("EUR", 2),
                Currency("JPY", 0), 2)
        return SpotOrder(pair, SELL, Amount(pair.baseCurrency, BigDecimal("1000")),
                LocalDate.now().plusDays(1), LocalDate.now())
    }

}