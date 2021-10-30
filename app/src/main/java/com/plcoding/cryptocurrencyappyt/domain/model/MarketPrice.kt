package com.plcoding.cryptocurrencyappyt.domain.model

data class MarketPrice(
    val close: Double,
    val high: Double,
    val low: Double,
    val marketCap: Long,
    val open: Double,
    val volume: Long
)