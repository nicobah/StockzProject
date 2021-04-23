package com.example.stockzprojectapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockzprojectapp.models.Repository
import kotlinx.coroutines.launch
import kotlin.random.Random.Default.nextInt

class MarketViewModel(private val repository: Repository): ViewModel() {



    fun getPopularLists(){
        viewModelScope.launch {
            val res = repository.getPopularLists()
//            println("FINANCE IS CALLED")
//            println(res.body()!!.finance.result[0].portfolios[0].pfId)
//            for(portfolioName in res.body()!!.finance.result[0].portfolios){
//                println(portfolioName.pfId)
//                println(portfolioName.userId)
//
//            }
            val listOfPortfoliData = res.body()!!.finance.result[0].portfolios
            val random = nextInt(listOfPortfoliData.size - 1)
            val chosenPortData = listOfPortfoliData[random]
            println(chosenPortData)
            println("Next Call")
            val watchListDetails = repository.getWatchListDetail(chosenPortData.pfId, chosenPortData.userId)
//            println(watchListDetails.body()?.finance!!.result[0].portfolios[0].positions)
            val listOfPositions = watchListDetails.body()?.finance!!.result[0].portfolios[0].positions
            println(watchListDetails.body()?.finance!!.result[0].portfolios[0].pfId)
            val symbolList = arrayListOf<String>()
            for (i in listOfPositions ) {
                symbolList.add(i.symbol)
            }
            println(symbolList)
        }
    }

}