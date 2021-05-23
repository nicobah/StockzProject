package com.example.stockzprojectapp.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlin.properties.Delegates

class Stock(symbol: String, price: Float, date: String, amount: Int = 0) {
    var symbol : String = symbol
    var price : Float = price
    var date : String = date
    var amount: Int = amount

    fun addAmount(amount: Int){
        this.amount += amount
    }
}