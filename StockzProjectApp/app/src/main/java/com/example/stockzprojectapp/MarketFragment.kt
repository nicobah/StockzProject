package com.example.stockzprojectapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MarketFragment : Fragment(R.layout.fragment_portfolio), View.OnClickListener  {

    lateinit var stockText : EditText
    lateinit var resultText : EditText
    lateinit var portfolioButton : Button
    lateinit var numberOfStocks: EditText
    lateinit var dateKey : String
    lateinit var json: JSONObject
    var myPortfolio = PortfolioFragment()
    lateinit var myStock : Stock

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {        val view: View = inflater!!.inflate(R.layout.fragment_market, container, false)
        val btn: Button = view.find(R.id.searchButtonId)
        stockText = view.findViewById(R.id.textStockId)
        numberOfStocks = view.findViewById(R.id.numberOfStockId)
        resultText = view.findViewById(R.id.textResultId)
        portfolioButton = view.findViewById(R.id.portfolioButtonId)
        btn.setOnClickListener(this)
        portfolioButton.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.searchButtonId -> {
                println("Stock: " + stockText.text.toString())
                val stockPrice = execute(stockText.text.toString())}
            R.id.portfolioButtonId -> {
                myStock.addAmount(numberOfStocks.text.toString().toInt())
                myPortfolio.addToPortfolio(myStock)
            }
                else -> {}
        }

    }

    fun execute(symbol: String) {
        val url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=5min&apikey=KX1X7LVV24I06XJE".format(symbol)
        // send the URL request and read the response as a asyncrone call
        val fetch = doAsync{
            val result = URL(url).readText()
            json = JSONObject(result)
            dateKey = ((json["Meta Data"] as JSONObject).getString("3. Last Refreshed"))
            var price = getPrice(result)
            if (price == -1.0f){
                resultText.setText("Stock not found")
                numberOfStocks.isEnabled = false
                portfolioButton.isEnabled = false
            } else {
                uiThread {
                    myStock = Stock(symbol, price, dateKey)
                    var myString = myStock.symbol + System.getProperty("line.separator") + myStock.date + System.getProperty("line.separator")  +"$" + myStock.price
                    resultText.setText(myString)
                    numberOfStocks.setText("0")
                    numberOfStocks.isEnabled = true
                    portfolioButton.isEnabled = true
                }
            }

        }
    }

    fun getPrice(text: String): Float {
        if (text.trim().length == 0 || ("Invalid" in text)) return-1.0f
        val message = text
// get price from today
        val todaysStockPrices =
            ((json["Time Series (5min)"] as JSONObject)[dateKey.toString()] as JSONObject).getString("1. open")
        return todaysStockPrices.toFloat()
    }
}