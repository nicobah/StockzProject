package com.example.stockzprojectapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockzprojectapp.models.Repository
import com.example.stockzprojectapp.models.Stock
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.random.Random.Default.nextInt

class MarketViewModel(private val repository: Repository): ViewModel() {
    lateinit var dateKey: String
    lateinit var json: JSONObject


    fun getPopularLists(){
        viewModelScope.launch {
            val res = repository.getPopularLists()

            val listOfPortfoliData = res.body()!!.finance.result[0].portfolios
            val random = nextInt(listOfPortfoliData.size - 1)
            val chosenPortData = listOfPortfoliData[random]
            val watchListDetails = repository.getWatchListDetail(chosenPortData.pfId, chosenPortData.userId)
            val listOfPositions = watchListDetails.body()?.finance!!.result[0].portfolios[0].positions
            println(watchListDetails.body()?.finance!!.result[0].portfolios[0].pfId)
            val symbolList = arrayListOf<String>()
            for (i in listOfPositions ) {
                symbolList.add(i.symbol)
            }
            val randTest = repository.getStockPrice("TSLA").body()!!.price.regularMarketOpen.raw
            val stockList = createStockList(symbolList)
            Log.d("HEY", stockList[5].symbol)
            Log.d("HEY", stockList[5].price.toString())

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