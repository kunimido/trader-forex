package com.github.kunimido.trader.forex.service.currency

import com.github.kunimido.trader.forex.domain.CurrencyPair
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CurrencyPairService(
        private val currencyPairRepository: CurrencyPairRepository,
        private val currencyPairMapper: CurrencyPairMapper
) {

    fun findByCode(code: String): CurrencyPair {
        val pair = requireNotNull(currencyPairRepository.findByCode(code)) {
            "No CurrencyPair found with code $code"
        }
        return currencyPairMapper.map(pair)
    }

}