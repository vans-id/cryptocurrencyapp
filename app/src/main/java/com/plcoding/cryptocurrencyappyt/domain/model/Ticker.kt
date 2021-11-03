package com.plcoding.cryptocurrencyappyt.domain.model

data class Ticker(
    val id: String,
    val name: String,
    val rank: Int,
    val symbol: String,
    val price: Double,
    val percentChange24h: Double,
    val percentChange7d: Double,
    val percentChange1y: Double,
)