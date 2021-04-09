package com.example.stockzprojectapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Stock
{
    var symbol : String = ""
    var price : Float = 0.0f

    constructor(symbol1: String, price1 : Float){
        symbol = symbol1
        price = price1
    }
}