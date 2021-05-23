package com.example.stockzprojectapp.models

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YahooApi {

    @GET("market/get-popular-watchlists")
    suspend fun getPopularLists(): Response<Finance>

    @GET("market/get-watchlist-detail")
    suspend fun getWatchListDetail(@Query("pfId") pfId: String, @Query("userId") userId: String): Response<Finance>

}


data class Finance(val finance:FinanceData )
data class FinanceData(val result: List<ResultData> , val error: String)
data class ResultData(val id: String, val name: String, val total: Int, val portfolios: List<PortfolioData>, val quotes: JsonObject)
data class PortfolioData(val userId: String, val pfId: String, val name: String, val positions: List<PositionData>)
data class PositionData(val symbol: String)
data class JsonCasted(val longName: String, val regularMarketOpen: String, val forwardPE: String)

