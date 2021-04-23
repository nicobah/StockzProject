package com.example.stockzprojectapp.models

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.Result

interface YahooApi {

    @GET("get-popular-watchlists")
    suspend fun getPopularLists(): Response<Finance>

    @GET("get-watchlist-detail")
    suspend fun getWatchListDetail(@Query("pfId") pfId: String, @Query("userId") userId: String): Response<Finance>
}

data class Finance(val finance:FinanceData )
data class FinanceData(val result: List<ResultData> , val error: String)
data class ResultData(val id: String, val name: String, val total: Int, val portfolios: List<PortfolioData>)
data class PortfolioData(val userId: String, val pfId: String, val positions: List<PositionsData>)
data class PositionsData(val symbol: String)

