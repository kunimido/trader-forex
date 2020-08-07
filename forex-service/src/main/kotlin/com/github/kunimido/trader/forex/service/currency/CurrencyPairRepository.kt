package com.github.kunimido.trader.forex.service.currency

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class CurrencyPairRepository : PanacheRepository<CurrencyPairEntity> {

    fun findByCode(code: String) = find("""
        select p
        from CurrencyPair p
            inner join fetch p.baseCurrency
            inner join fetch p.quoteCurrency
        where p.code = :code
        """.trimIndent(), mapOf("code" to code)).firstResult()

}