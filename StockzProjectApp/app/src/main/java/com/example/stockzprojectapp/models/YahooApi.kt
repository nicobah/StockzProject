package com.example.stockzprojectapp.models

import retrofit2.Response
import retrofit2.http.GET

interface YahooApi {

    @GET("get-popular-watchlists")
    suspend fun getPopularLists(): Response<List<String>>
}