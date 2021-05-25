package com.example.stockzprojectapp.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.stockzprojectapp.viewmodels.PortfolioViewModel
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.databinding.FragmentDetailedPortfolioBinding
import com.example.stockzprojectapp.models.Stock
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class PortfolioDetailedFragment(symbol: String = "") : Fragment(R.layout.fragment_detailed_portfolio){

    lateinit var dateKey : String
    lateinit var json: JSONObject
    private var symbol: String = symbol

    private val portfolioViewModel: PortfolioViewModel by activityViewModels()
    //private lateinit var stockName: TextView
    private lateinit var stockValue: TextView
    private lateinit var todayRise: TextView
    private lateinit var todayHigh: TextView
    private lateinit var todayLow: TextView
    private lateinit var openingPrice: TextView
    private lateinit var binding: FragmentDetailedPortfolioBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailedPortfolioBinding.bind(view)

        stockValue = binding.stockPrice
        todayRise = binding.todayRise
        todayHigh = binding.todayHigh
        todayLow = binding.todayLow
        openingPrice = binding.openingPrice

        /*val portfolioObserver = Observer<Pair<Int, Stock>>{
            binding.stockSymbol.setText("${it.second.symbol}")
            execute("${it.second.symbol}")
        }*/

        binding.stockSymbol.setText("$symbol")
        execute("$symbol")

        //portfolioViewModel.getSelectedStock().observe(viewLifecycleOwner, portfolioObserver)
    }

    fun execute(symbol: String) {
        val url5min = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=5min&apikey=KX1X7LVV24I06XJE".format(symbol)
        val urlDaily = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=KX1X7LVV24I06XJE".format(symbol)
        // send the URL request and read the response as a asyncrone call
        println(symbol)

        val fetch = doAsync{
            val result = URL(url5min).readText()
            json = JSONObject(result)
            dateKey = ((json["Meta Data"] as JSONObject).getString("3. Last Refreshed"))
            var information = getInfo(json,"Time Series (5min)","1. open", result)
            //var percentRise = ((informationDaily.toDouble() - information5min.toDouble())/informationDaily.toDouble())
            if (information == "Invalid"){
            } else {
                uiThread {
                    //stockName.text = "Not possible"
                    stockValue.text = "%.2f".format(information.toDouble())
                }
            }

            val fetch = doAsync {
                val result = URL(urlDaily).readText()
                json = JSONObject(result)
                dateKey = ((json["Meta Data"] as JSONObject).getString("3. Last Refreshed"))
                var information = getInfo(json, "Time Series (Daily)", "4. close", result)
                println(information)
                println(stockValue.text)
                var percentRise =
                    ((information.toDouble()-stockValue.text.toString().toDouble()) / information.toDouble())
                var high = getInfo(json, "Time Series (Daily)", "2. high", result).toDouble()
                if (information == "Invalid") {
                } else {
                    uiThread {
                        //stockName.text = "Not possible"
                        todayRise.text = "%.2f".format(percentRise * 100) + "% - $" + "%.2f".format(stockValue.text.toString().toDouble() * percentRise)
                        //var myString = myStock.symbol + System.getProperty("line.separator") + myStock.date + System.getProperty("line.separator")  +"$" + myStock.price
                        todayLow.text = "Todays low: $" + "%.2f".format(getInfo(json, "Time Series (Daily)", "3. low", result).toDouble())
                        todayHigh.text = "Todays high: $" + "%.2f".format(high)
                        openingPrice.text = "Todays opening price: $" + "%.2f".format(getInfo(json, "Time Series (Daily)", "1. open", result).toDouble())
                    }
                }
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