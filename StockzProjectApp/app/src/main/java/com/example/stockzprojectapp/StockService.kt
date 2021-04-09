package com.example.stockzprojectapp

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*



class StockService {
    fun execute(symbol: String, date: String, output: String) {
        val url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=%s&apikey=KX1X7LVV24I06XJE".format(symbol)
        var price = 0f

        doAsync {      // send the URL request and read the response
            val result = URL(url).readText()     //  Parse out the price from the JSON result
            price = getPrice(result, date, output)
            println(price)
            uiThread {          //  add the resulting symbol and price of the stock to the adapter

            }
        }
    }

    fun getPrice(text: String, date: String, output: String): Float {
        if (text.trim().length == 0 || ("Invalid" in text)) return-1.0f
            val message = text
            val json = JSONObject(message)
// get price from today
            val today = Date()
            var DATE_FORMAT =  SimpleDateFormat("yyyy-MM-dd")
            var dateKey = DATE_FORMAT.format(today) as String
            val todaysStockPrices =
                ((json["Time Series (Daily)"] as JSONObject)["2021-04-06"] as JSONObject).getString(output)
                return todaysStockPrices.toFloat()
    }
}