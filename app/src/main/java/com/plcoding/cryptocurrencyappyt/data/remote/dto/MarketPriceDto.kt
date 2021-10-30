package com.plcoding.cryptocurrencyappyt.data.remote.dto

import com.plcoding.cryptocurrencyappyt.domain.model.MarketPrice

data class MarketPriceDto(
    val close: Double,
    val high: Double,
    val low: Double,
    val market_cap: Long,
    val open: Double,
    val time_close: String,
    val time_open: String,
    val volume: Long
)

fun MarketPriceDto.toMarketPrice(): MarketPrice {
    return MarketPrice(
        close,
        high,
        low,
        marketCap = market_cap,
        open,
        volume
    )
}