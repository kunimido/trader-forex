package com.github.kunimido.trader.forex.service.currency

import com.github.kunimido.trader.forex.domain.Currency
import com.github.kunimido.trader.forex.domain.CurrencyPair
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CurrencyPairMapper {

    fun map(source: CurrencyPairEntity): CurrencyPair {
        return CurrencyPair(
                Currency(source.baseCurrency.code, source.baseCurrency.decimalPlaces),
                Currency(source.quoteCurrency.code, source.quoteCurrency.decimalPlaces),
                source.decimalPlaces
        )
    }

}