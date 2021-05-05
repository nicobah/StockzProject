package com.example.stockzprojectapp.models

import retrofit2.Response

class Repository {

    suspend fun getPopularLists(): Response<Finance>{
        return RetrofitInstance.yahooApi.getPopularLists()
    }
    suspend fun getWatchListDetail(pfId: String, userId: String): Response<Finance>{
        return RetrofitInstance.yahooApi.getWatchListDetail(pfId, userId)
    }

    suspend fun getStockPrice(symbol: String): Response<FinancialsTemplate>{
        return RetrofitInstance.yahooApi.getStockPrice(symbol)
    }
    suspend fun getTimeSeriesIntraDay(symbol: String): Response<GlobalQuote>{
        return RetrofitInstance.alphaApi.getStockInfo( "GLOBAL_QUOTE", symbol)
    }
}