package com.example.stockzprojectapp.models

class Portfolio {

    private var totalValue: Double

    private var stats: Double

    private var myStocks: MutableMap<String, Int>

    init {
        this.totalValue = 0.0
        this.stats = 0.0
        this.myStocks = mutableMapOf()
    }

    fun setTotalValue(value: Double){
        this.totalValue = value
    }

    fun setStats(stats: Double){
        this.stats = stats
    }

    fun getTotalValue() : Double{
        return this.totalValue
    }

    fun getStats() : Double{
        return this.stats
    }

    fun getTotalValueString() : String{
        return this.totalValue.toString()
    }

    fun getStatsString() : String{
        return this.stats.toString() + "%"
    }

    fun addStocks(stockSymbol: String, amount: Int){
        var totalAmount = amount

        if(this.myStocks.containsKey(stockSymbol)){
             totalAmount += this.myStocks[stockSymbol]!!
        }

        this.myStocks[stockSymbol] = amount
    }
}