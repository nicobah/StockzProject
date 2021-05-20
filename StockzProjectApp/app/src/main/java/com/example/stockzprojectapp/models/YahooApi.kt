package com.example.stockzprojectapp.models

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YahooApi {

    @GET("market/get-popular-watchlists")
    suspend fun getPopularLists(): Response<Finance>

    @GET("market/get-watchlist-detail")
    suspend fun getWatchListDetail(@Query("pfId") pfId: String, @Query("userId") userId: String): Response<Finance>

    @GET("stock/v2/get-summary")
    suspend fun getStockInfo(@Query("symbol") symbol: String) : Response<FinancialsTemplate>


}


data class Finance(val finance:FinanceData )
data class FinanceData(val result: List<ResultData> , val error: String)
data class ResultData(val id: String, val name: String, val total: Int, val portfolios: List<PortfolioData>)
data class PortfolioData(val userId: String, val pfId: String, val name: String, val positions: List<PositionData>)
data class PositionData(val symbol: String)
data class FinancialsTemplate(val price: Price, val defaultKeyStatistics: DefKeyStatsData, val summaryProfile: SumProfile)
data class SumProfile(val sector: String)
data class DefKeyStatsData(val forwardPE: Raw)
data class Price(val regularMarketOpen: Raw)
data class Raw(val raw: String)


