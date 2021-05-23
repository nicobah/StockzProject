package com.example.stockzprojectapp.models

class Stock(symbol: String, price: Float, date: String, amount: Int = 0, sector: String = "None", peRatio: Float = 0F) {
    var symbol: String = symbol
    var price: Float = price
    var date: String = date
    var amount: Int = 0
    var sector = sector
    var peRatio = peRatio

    fun addAmount(amount: Int) {
        this.amount += amount
    }

    fun addSector(sector: String) {
    this.sector = sector
}
    fun addPE(pe: Float){
        this.peRatio = pe
    }
}