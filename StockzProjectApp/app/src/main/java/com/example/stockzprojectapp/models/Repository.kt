package com.example.stockzprojectapp.models

import retrofit2.Response

class Repository {

    suspend fun getPopularLists(): Response<Finance>{
        return RetrofitInstance.yahooApi.getPopularLists()
    }
    suspend fun getWatchListDetail(pfId: String, userId: String): Response<Finance>{
        return RetrofitInstance.yahooApi.getWatchListDetail(pfId, userId)
    }
}