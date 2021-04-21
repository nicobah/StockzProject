package com.example.stockzprojectapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stockzprojectapp.models.Stock

class PortfolioViewModel : ViewModel() {

    private var stocks: ArrayList<Stock>
    private var selectedStock = MutableLiveData<Pair<Int, Stock>>()
    //private val stockManager = StockManager()

    init {
        this.stocks = generateStockList()
    }

    fun getSelectedStock(): LiveData<Pair<Int, Stock>> {
        return selectedStock
    }

    fun selectStockAt(position: Int) {
        selectedStock.value = Pair(position, stocks[position])
    }

    fun getStocks(): ArrayList<Stock> {
        return stocks
    }

    fun addStock(stock: Stock) {
        this.stocks.add(stock)
    }

    private fun generateStockList(): ArrayList<Stock>{
        val list = ArrayList<Stock>()
        for (i in 1..10){
            val myStock = Stock(
                "IBM",
                200.0f,
                "16-04-2021 20:00:00"
            )
            list.add(myStock)
        }
        return list
    }
}