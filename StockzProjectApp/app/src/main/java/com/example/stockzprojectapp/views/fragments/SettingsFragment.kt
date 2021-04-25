package com.example.stockzprojectapp.views.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.models.Stock

class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    fun loadData(){
        val sharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val stockSymbol = sharedPreferences.getString("STOCK2", "")!!
        val stockAmount = sharedPreferences.getInt(stockSymbol, 0)
    }

    private fun saveData(stock: Stock){
        val sharedPreferences = requireActivity().getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply {
            putString("STOCK2", stock.symbol)
            putInt(stock.symbol, 20)
        }.apply()
    }

}