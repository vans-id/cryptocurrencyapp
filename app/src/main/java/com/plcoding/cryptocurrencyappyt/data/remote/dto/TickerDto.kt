package com.plcoding.cryptocurrencyappyt.data.remote.dto

import com.plcoding.cryptocurrencyappyt.domain.model.Ticker

data class TickerDto(
    val id: String,
    val last_updated: String,
    val name: String,
    val quotes: Quotes,
    val rank: Int,
    val symbol: String,
)

fun TickerDto.toTicker(): Ticker {
    return Ticker(
        id, name, rank, symbol,
        price = quotes.USD.price,
        percentChange24h = quotes.USD.percent_change_24h,
        percentChange7d = quotes.USD.percent_change_7d,
        percentChange1y = quotes.USD.percent_change_1y
    )
}