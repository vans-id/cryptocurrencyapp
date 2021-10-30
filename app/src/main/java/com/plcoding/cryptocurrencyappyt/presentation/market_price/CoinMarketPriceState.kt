package com.plcoding.cryptocurrencyappyt.presentation.market_price

import com.plcoding.cryptocurrencyappyt.domain.model.MarketPrice

data class CoinMarketPriceState(
    val isLoading: Boolean = false,
    val coin: MarketPrice? = null,
    val error: String = ""
)
