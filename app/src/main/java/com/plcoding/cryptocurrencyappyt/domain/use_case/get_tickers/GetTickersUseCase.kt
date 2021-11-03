package com.plcoding.cryptocurrencyappyt.domain.use_case.get_tickers

import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toCoin
import com.plcoding.cryptocurrencyappyt.data.remote.dto.toTicker
import com.plcoding.cryptocurrencyappyt.domain.model.Ticker
import com.plcoding.cryptocurrencyappyt.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTickersUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Ticker>>> = flow {
        try {
            emit(Resource.Loading<List<Ticker>>())
            val tickers = repository.getTickers().map {
                it.toTicker()
            }
            emit(Resource.Success<List<Ticker>>(tickers))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<Ticker>>(
                    e.localizedMessage ?: "An expected error occured"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Ticker>>(
                    "Couldn't reach the server. Check your internet connection"
                )
            )
        }
    }
}





























