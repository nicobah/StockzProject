package com.example.stockzprojectapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stockzprojectapp.models.DummyItem

class PortfolioViewModel : ViewModel() {

    private lateinit var stocks: ArrayList<DummyItem>
    private lateinit var stockSymbols: ArrayList<String?>
    private var selectedStock = MutableLiveData<Pair<Int, DummyItem>>()
    //private val stockManager = StockManager()

    init {
        loadDummies()
        loadDummySymbols()
    }

    fun getSelectedStock(): LiveData<Pair<Int, DummyItem>> {
        return selectedStock
    }

    fun selectStockAt(position: Int) {
        selectedStock.value = Pair(position, stocks[position])
    }

    fun getStocks(): ArrayList<DummyItem> {
        return stocks
    }

    fun getSymbols(): ArrayList<String?> {
        return stockSymbols
    }

    private fun loadDummySymbols() {
        val symbols = ArrayList<String?>()
        for (i in stocks){
            symbols.add(i.name)
        }
        stockSymbols = symbols
    }

    private fun loadDummies() {
        val list = ArrayList<DummyItem>()
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(
            DummyItem(
                "ItemNoGameStop",
                999999999
            )
        )
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(
            DummyItem(
                "ItemNoGameStop",
                999999999
            )
        )
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(
            DummyItem(
                "ItemNoGameStop",
                999999999
            )
        )
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(
            DummyItem(
                "ItemNoGameStop",
                999999999
            )
        )
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(
            DummyItem(
                "ItemNoGameStop",
                999999999
            )
        )
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(
            DummyItem(
                "ItemNoGameStop",
                999999999
            )
        )

        stocks = list
    }


}