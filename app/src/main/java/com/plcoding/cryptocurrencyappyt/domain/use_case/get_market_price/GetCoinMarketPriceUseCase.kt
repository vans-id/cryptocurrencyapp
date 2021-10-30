package com.plcoding.cryptocurrencyappyt.domain.use_case.get_market_price

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toMarketPrice
import com.plcoding.cryptocurrencyappyt.domain.model.MarketPrice
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinMarketPriceUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<MarketPrice>> =
        flow {
            try {
                emit(Resource.Loading<MarketPrice>())
                val price =
                    repository.getCoinMarketPrice(coinId)
                        .toMarketPrice()
                emit(Resource.Success<MarketPrice>(price))
            } catch (e: HttpException) {
                emit(
                    Resource.Error<MarketPrice>(
                        e.localizedMessage
                            ?: "An expected error occured"
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error<MarketPrice>("Couldn't reach the server. Check your internet connection")
                )
            }
        }
}