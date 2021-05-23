package com.example.stockzprojectapp.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.stockzprojectapp.models.PortfolioData
import com.example.stockzprojectapp.models.Repository
import com.example.stockzprojectapp.models.Stock
import com.example.stockzprojectapp.views.ProgressBar
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.random.Random.Default.nextInt

class InspirationViewModel(private val repository: Repository, private val savedStateHandle: SavedStateHandle) : ViewModel() {

    private var stockArray = MutableLiveData<ArrayList<Stock>>()
    private var portfolioName = MutableLiveData<String>()
    private lateinit var listOfPortfoliData: List<PortfolioData>
    private var resHasBeenCalled = false
    private var isLoading = MutableLiveData<Boolean>()


    init {
        stockArray.value = arrayListOf()
        getPopularListsResponseAndSetWatchList()
    }

    fun getPortfolioName(): LiveData<String> {
        return portfolioName
    }

    fun getStocks(): LiveData<ArrayList<Stock>> {
        return stockArray
    }

    fun getIsLoading():LiveData<Boolean>{
        return isLoading
    }






    fun getPopularListsResponseAndSetWatchList() {
        viewModelScope.launch {
            if (!resHasBeenCalled) try {
                val res = repository.getPopularLists()
                listOfPortfoliData = res.body()!!.finance.result[0].portfolios
                resHasBeenCalled = true
                setWatchList()
            } catch (e: Exception) {
                println("Exception")
            }
        }
    }

    fun setWatchList() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val random = nextInt(listOfPortfoliData.size - 1)
            val chosenPortfolio = listOfPortfoliData[random]
            val watchListDetails =
                repository.getWatchListDetail(chosenPortfolio.pfId, chosenPortfolio.userId)
            portfolioName.postValue(chosenPortfolio.name)
            val listOfPositions =
                watchListDetails.body()!!.finance.result[0].portfolios[0].positions
            val symbolList = arrayListOf<String>()
            for (i in listOfPositions) {
                symbolList.add(i.symbol)
            }
            val stockList = createStockList(symbolList)

            stockArray.postValue(stockList)
            isLoading.postValue(false)
        }

    }

    private suspend fun createStockList(list: ArrayList<String>): ArrayList<Stock> {
        val result = ArrayList<Stock>()
        for (i in list) try {
            var call = repository.getStockInfo(i)
            if (call.isSuccessful) {
                var price = call.body()!!.price.regularMarketOpen.raw
                var priceEarnings = call.body()!!.defaultKeyStatistics.forwardPE.raw
                var sector = call.body()!!.summaryProfile.sector
                val stockI = Stock(i, price.toFloat(), "none")
                stockI.addPE(priceEarnings.toFloat())
                stockI.addSector(sector)
                result.add(stockI)
            }
        } catch (e: Exception) {
            println("Exception")
        }
        return result
    }

}

