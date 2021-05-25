package com.example.stockzprojectapp.models

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val yahooClient = OkHttpClient.Builder().apply {
        addInterceptor(YahooInterceptor())
    }.build()

    val yahooApi: YahooApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://apidojo-yahoo-finance-v1.p.rapidapi.com/")
            .client(yahooClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(YahooApi::class.java)
    }

}

class YahooInterceptor: Interceptor {
    //Responsible for adding the apikey too all requests sent.
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("x-rapidapi-key",  "f56c954a60mshc538fa5800a9751p1890c5jsn5fb3e384aaa2" )
            .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com" )
            .build()
        return chain.proceed(request)
    }
}
