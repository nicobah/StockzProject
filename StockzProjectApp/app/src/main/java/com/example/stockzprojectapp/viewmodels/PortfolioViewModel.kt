package com.example.stockzprojectapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stockzprojectapp.models.Stock

class PortfolioViewModel : ViewModel() {

    private var stocks: ArrayList<Stock> = arrayListOf()
    private var selectedStock = MutableLiveData<Pair<Int, Stock>>()

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

    fun clearStocks(){
        this.stocks = arrayListOf()
    }

    fun clearSelectedStock(){
        this.selectedStock = MutableLiveData<Pair<Int, Stock>>()
    }
}