package com.example.stockzprojectapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL


class DetailedViewModel: ViewModel() {

    lateinit var dateKey : String
    lateinit var json: JSONObject
    private var stockPrice = MutableLiveData<String>()
    private var percentRise = MutableLiveData<String>()
    private var todayHigh = MutableLiveData<Float>()
    private var todayLow = MutableLiveData<Float>()
    private var openingPrice = MutableLiveData<Float>()

    fun getStockPrice(): LiveData<String> {
        return stockPrice
    }

    fun getPercentRise(): LiveData<String> {
        return percentRise
    }
    fun getTodayHigh(): LiveData<Float>{
        return todayHigh
    }
    fun getTodayLow(): LiveData<Float>{
        return todayLow
    }
    fun getOpeningPrice(): LiveData<Float>{
        return openingPrice
    }




    fun execute(symbol: String) {
        val url5min = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=5min&apikey=KX1X7LVV24I06XJE".format(symbol)
        val urlDaily = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=KX1X7LVV24I06XJE".format(symbol)
        // send the URL request and read the response as a asyncrone call
        println(symbol)

        doAsync{
            val result = URL(url5min).readText()
            json = JSONObject(result)
            dateKey = ((json["Meta Data"] as JSONObject).getString("3. Last Refreshed"))
            var information = getInfo(json,"Time Series (5min)","1. open", result)

            //var percentRise = ((informationDaily.toDouble() - information5min.toDouble())/informationDaily.toDouble())
            if (information == "Invalid"){
            } else {
                    stockPrice.postValue(information)
            }
            doAsync {
                val result = URL(urlDaily).readText()
                json = JSONObject(result)
                dateKey = ((json["Meta Data"] as JSONObject).getString("3. Last Refreshed"))
                var information = getInfo(json, "Time Series (Daily)", "4. close", result)

                var rise = (information.toFloat()-getStockPrice().value!!.toFloat()) / information.toFloat()*100


                percentRise.postValue("%.2f".format(rise.toDouble()) + "%")
                todayHigh.postValue( getInfo(json, "Time Series (Daily)", "2. high", result).toFloat())
                todayLow.postValue(getInfo(json, "Time Series (Daily)", "3. low", result).toFloat())
                openingPrice.postValue(getInfo(json, "Time Series (Daily)", "1. open", result).toFloat())
            }
        }
    }


    fun getInfo(json: JSONObject, timeFrame: String, name: String, text: String): String {
        if (text.trim().length == 0 || ("Invalid" in text)) return "Invalid"
        val message = text
// get information from today
        val information =
            ((json[timeFrame] as JSONObject)[dateKey.toString()] as JSONObject).getString(name)
        return information
    }

}