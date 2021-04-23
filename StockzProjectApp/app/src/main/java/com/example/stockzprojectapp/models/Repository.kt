package com.example.stockzprojectapp.models

import retrofit2.Response

class Repository {

    suspend fun getPopularLists(): Response<List<String>>{
        return RetrofitInstance.api.getPopularLists()
    }
}