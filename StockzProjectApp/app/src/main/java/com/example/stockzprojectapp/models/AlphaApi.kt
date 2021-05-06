package com.example.stockzprojectapp.models

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


interface AlphaApi {

    @GET("query")
    suspend fun getStockInfo(
        @Query("function") function: String,
        @Query("symbol") symbol: String
    ): Response<GlobalQuote>

}

data class GlobalQuote(@SerializedName("Global Quote") val globalQuote: GlobalQuoteData)

data class GlobalQuoteData(
    @SerializedName("07. latest trading day") val ltd: String,
    @SerializedName("05. price") val price: String
)
