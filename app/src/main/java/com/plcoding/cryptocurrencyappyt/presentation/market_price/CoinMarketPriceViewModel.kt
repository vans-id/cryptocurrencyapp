package com.plcoding.cryptocurrencyappyt.presentation.market_price

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Constants
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.use_case.get_market_price.GetCoinMarketPriceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinMarketPriceViewModel @Inject constructor(
    private val getMarketPriceUseCase: GetCoinMarketPriceUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state =
        mutableStateOf<CoinMarketPriceState>(CoinMarketPriceState())
    val state: State<CoinMarketPriceState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)
            ?.let { coinId ->
                getCoin(coinId)
            }
    }

    private fun getCoin(coinId: String) {
        getMarketPriceUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CoinMarketPriceState(
                        coin = result.data
                    )
                }
                is Resource.Error -> {
                    _state.value = CoinMarketPriceState(
                        error = result.message
                            ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value =
                        CoinMarketPriceState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


}



















