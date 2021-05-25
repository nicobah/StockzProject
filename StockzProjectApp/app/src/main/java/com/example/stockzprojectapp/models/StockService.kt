package com.example.stockzprojectapp.models

import org.json.JSONObject
import java.net.URL

class StockService {

    lateinit var json: JSONObject
    var price: Float = 0F

    fun execute(symbol: String, url: String): String {
        val result = URL(url).readText()
        json = JSONObject(result)
        return ((json["Meta Data"] as JSONObject).getString("3. Last Refreshed"))
    }

    fun getPrice(text: String, dateKey: String): Float {
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
