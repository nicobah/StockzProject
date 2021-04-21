package com.example.stockzprojectapp.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlin.properties.Delegates

class Stock(symbol1: String, price1: Float, date1: String) {
    var symbol : String = symbol1
    var price : Float = price1
    var date : String = date1
    var amount: Int = 0

    public fun addAmount(amount: Int){
        this.amount += amount
    }
}