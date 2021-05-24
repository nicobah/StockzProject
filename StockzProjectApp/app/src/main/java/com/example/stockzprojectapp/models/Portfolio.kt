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
        return "$%.2f".format(this.totalValue)
    }

    fun getStatsString() : String{
        return this.stats.toString() + "%"
    }
}