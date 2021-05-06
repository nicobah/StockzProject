package com.example.stockzprojectapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockzprojectapp.models.Repository
import com.example.stockzprojectapp.models.Stock
import com.example.stockzprojectapp.models.StockService
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.random.Random.Default.nextInt

class InspirationViewModel(private val repository: Repository): ViewModel() {
    lateinit var dateKey: String
    lateinit var json: JSONObject
    private var stockArray = MutableLiveData<ArrayList<Stock>>()
    private var portfolioName = MutableLiveData<String>()
    val stockService = StockService()

    init {
        stockArray.value = arrayListOf()
    }


    fun getStocks(): LiveData<ArrayList<Stock>>{
        return stockArray
    }
    fun getPortfolioName(): LiveData<String>{
        return portfolioName
    }

    fun getPopularLists(){
        val stockList = arrayListOf<Stock>()
        viewModelScope.launch {
            val testName = "Name!"
//            val res = repository.getPopularLists()
//
//            val listOfPortfoliData = res.body()!!.finance.result[0].portfolios
//            val random = nextInt(listOfPortfoliData.size - 1)
//            val chosenPortData = listOfPortfoliData[random]
//            val watchListDetails = repository.getWatchListDetail(chosenPortData.pfId, chosenPortData.userId)
//            val listOfPositions = watchListDetails.body()?.finance!!.result[0].portfolios[0].positions
//            println(watchListDetails.body()?.finance!!.result[0].portfolios[0].pfId)
//            val symbolList = arrayListOf<String>()
//            for (i in listOfPositions ) {
//                symbolList.add(i.symbol)
//            }
//            val randTest = repository.getStockPrice("TSLA").body()!!.price.regularMarketOpen.raw
//            val stockList = createStockList(symbolList)
//            val symbol = "TSLA"
//            val test = repository.getTimeSeriesIntraDay(symbol)
//            println(test.body()!!.globalQuote.price)
//            val stock = Stock(symbol, test.body()!!.globalQuote.price.toFloat(), "tempDate")
            for(i in 1..10) {
                stockList.add(Stock("$i", i.toFloat(), "$i"))
            }

            stockArray.postValue(stockList)
            portfolioName.postValue(testName)

        }

    }

    private suspend fun createStockList(list: ArrayList<String>): ArrayList<Stock>{
        val result = ArrayList<Stock>()
        for(i in list){
            val price = repository.getStockPrice(i).body()!!.price.regularMarketOpen.raw
            result.add(Stock(i, price, "random"))
        }
        return result
    }



}