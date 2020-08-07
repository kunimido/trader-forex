package com.github.kunimido.trader.forex.service.currency

import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity(name = "Currency")
@Table(name = "currency", schema = "trader_forex")
data class CurrencyEntity(

        @Id
        @Column(name = "currency_id")
        var id: Long? = null,

        @Column(name = "currency_uuid")
        var uuid: UUID,

        var code: String,

        @Column(name = "decimal_places")
        var decimalPlaces: Int

)