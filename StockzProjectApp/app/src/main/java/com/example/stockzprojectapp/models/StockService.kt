package com.example.stockzprojectapp.models

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


class StockService {

    lateinit var dateKey: String
    lateinit var json: JSONObject
    var price: Float = 0F

    fun execute(symbol: String) {
        val url =
            "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=5min&apikey=KX1X7LVV24I06XJE".format(
                symbol
            )
        // send the URL request and read the response as a asyncrone call
        val fetch = doAsync {
            val result = URL(url).readText()
            json = JSONObject(result)
            dateKey = ((json["Meta Data"] as JSONObject).getString("3. Last Refreshed"))
            price = getPrice(result)
        }
    }

    fun getPrice(text: String): Float {
        if (text.trim().length == 0 || ("Invalid" in text)) return -1.0f
        val message = text
        // get price from today
        val todaysStockPrices =
            ((json["Time Series (5min)"] as JSONObject)[dateKey.toString()] as JSONObject).getString(
                "1. open"
            )
        return todaysStockPrices.toFloat()
    }
}
