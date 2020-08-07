package com.github.kunimido.trader.forex.service.currency

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity(name = "CurrencyPair")
@Table(name = "currency_pair", schema = "trader_forex")
data class CurrencyPairEntity(

        @Id
        @Column(name = "currency_pair_id")
        var id: Long? = null,

        @Column(name = "currency_pair_uuid")
        var uuid: UUID,

        @ManyToOne
        @JoinColumn(name = "base_currency_id")
        var baseCurrency: CurrencyEntity,

        @ManyToOne
        @JoinColumn(name = "quote_currency_id")
        var quoteCurrency: CurrencyEntity,

        var code: String,

        @Column(name = "decimal_places")
        var decimalPlaces: Int

)