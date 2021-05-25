package com.example.stockzprojectapp.viewmodels

import androidx.lifecycle.*
import com.example.stockzprojectapp.models.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.random.Random.Default.nextInt

class InspirationViewModel(private val repository: Repository) : ViewModel() {

    private var stockArray = MutableLiveData<ArrayList<Stock>>()
    private var portfolioName = MutableLiveData<String>()
    private lateinit var listOfPortfolioData: List<PortfolioData>
    private var resHasBeenCalled = false
    private var isLoading = MutableLiveData<Boolean>()
    val gson = Gson()

    init {
        stockArray.value = arrayListOf()
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
                isLoading.postValue(true)
                val res = repository.getPopularLists()
                listOfPortfolioData = res.body()!!.finance.result[0].portfolios
                resHasBeenCalled = true
                setWatchList()
                isLoading.postValue(false)
            } catch (e: Exception) {
                println(e)
            }
        }
    }

    suspend fun setWatchList()  {
        if (this::listOfPortfolioData.isInitialized )try {
            viewModelScope.launch {
                isLoading.postValue(true)
                val random = nextInt(listOfPortfolioData.size - 1)
                val chosenPortfolio = listOfPortfolioData[random]
                val watchListDetails =
                    repository.getWatchListDetail(chosenPortfolio.pfId, chosenPortfolio.userId)
                val tempJson = watchListDetails.body()!!.finance.result[0].quotes
                portfolioName.postValue(chosenPortfolio.name)
                val listOfPositions =
                    watchListDetails.body()!!.finance.result[0].portfolios[0].positions
                var stockList = createListOfStocks(listOfPositions, tempJson)
                stockArray.postValue(stockList)
                isLoading.postValue(false)
            }
        } catch (e: Exception){
            println(e)
        }
    }

    private fun createListOfStocks(list: List<PositionData>, jsonObj: JsonObject): ArrayList<Stock>{
        val result = ArrayList<Stock>()
        for (i in list)  try {
            val p = jsonObj[i.symbol]
            val c = gson.fromJson(p, JsonCasted::class.java)
            var price = c.regularMarketOpen
            var priceEarnings = c.forwardPE
            val stockI = Stock(i.symbol, price.toFloat(), "none")
            stockI.addPE(priceEarnings.toFloat())
            stockI.addLongName(c.longName)
            result.add(stockI)
        } catch (e: Exception){
            println(e)
        }
        return result
    }

}



