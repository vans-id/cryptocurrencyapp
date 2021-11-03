package com.plcoding.cryptocurrencyappyt.presentation.coin_list

import com.plcoding.cryptocurrencyappyt.domain.model.Ticker

data class CoinListState(
    val isLoading: Boolean = false,
    val tickers: List<Ticker> = emptyList(),
    val error: String = ""
)
