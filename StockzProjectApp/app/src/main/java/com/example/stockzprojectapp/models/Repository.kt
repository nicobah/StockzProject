package com.example.stockzprojectapp.models

import retrofit2.Response

class Repository {

    suspend fun getPopularLists(): Response<Finance>{
        return RetrofitInstance.api.getPopularLists()
    }
    suspend fun getWatchListDetail(pfId: String, userId: String): Response<Finance>{
        return RetrofitInstance.api.getWatchListDetail(pfId, userId)
    }
}