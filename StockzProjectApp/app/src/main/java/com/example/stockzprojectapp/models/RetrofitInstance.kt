package com.example.stockzprojectapp.models

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    var logging = HttpLoggingInterceptor()


    private val yahooClient = OkHttpClient.Builder().apply {
        addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
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
    private val alphaClient = OkHttpClient.Builder().apply {
        addInterceptor(AlphaInterceptor())
        addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
    }.build()
    val alphaApi: AlphaApi by lazy{
        Retrofit.Builder()
            .baseUrl("https://alpha-vantage.p.rapidapi.com/")
            .client(alphaClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlphaApi::class.java)
    }


}

class YahooInterceptor: Interceptor {

    //Responsible for adding the apikey too all requests sent.
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("x-rapidapi-key",  "3062b13ca0msh9e8505e3240b9c5p1d46f9jsn5361e945c536" )
            .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com" )
            .build()
        return chain.proceed(request)
    }
}
class AlphaInterceptor: Interceptor {

    //Responsible for adding the apikey too all requests sent.
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("x-rapidapi-key",  "43603fd304msh82ca5fc6c15d32ep110207jsn4e229711d3d4" )
            .addHeader("x-rapidapi-host", "alpha-vantage.p.rapidapi.com" )
            .build()
        return chain.proceed(request)
    }
}
