package com.batuy.crypytoapp.api

import com.batuy.crypytoapp.pojo.CoinInfoListOfData
import com.batuy.crypytoapp.pojo.CoinPriceInfoRawData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = TOKEN,
        @Query(QUERY_PARAM_LIMIT) limit: Int = 10,
        @Query(QUERY_PARAM_TO_SYMBOL) tSymbol: String = CURRENCY
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = TOKEN,
        @Query(QUERY_PARAM_TO_SYMBOLS) tSymbols: String = CURRENCY,
        @Query(QUERY_PARAM_FROM_SYMBOLS) fSymbols: String,
    ): Single<CoinPriceInfoRawData>


    companion object {

        private const val QUERY_PARAM_LIMIT = "limit"
        private const val QUERY_PARAM_TO_SYMBOL = "tsym"
        private const val QUERY_PARAM_API_KEY = "api_key"
        private const val CURRENCY = "USD"
        private const val TOKEN = "056d4ee6f8bcc1da6fc63ed49e722a96f98646587b1e11d4635676b794be83e8"

        private const val QUERY_PARAM_TO_SYMBOLS = "tsyms"
        private const val QUERY_PARAM_FROM_SYMBOLS = "fsyms"
    }
}