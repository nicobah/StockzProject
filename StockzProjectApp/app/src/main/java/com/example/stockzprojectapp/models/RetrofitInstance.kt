package com.example.stockzprojectapp.models

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    //use logger for debugging api calls
    var logging = HttpLoggingInterceptor()


    private val yahooClient = OkHttpClient.Builder().apply {
        //addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
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
            .addHeader("x-rapidapi-key",  "43603fd304msh82ca5fc6c15d32ep110207jsn4e229711d3d4" )//0e46882a0amsh165ddbbfbef0134p158197jsn4f72e95d9562
            .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com" )
            .build()
        return chain.proceed(request)
    }
}
