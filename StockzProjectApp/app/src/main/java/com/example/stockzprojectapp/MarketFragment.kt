package com.example.stockzprojectapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.jetbrains.anko.find
import org.w3c.dom.Text

class MarketFragment : Fragment(), View.OnClickListener  {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {        val view: View = inflater!!.inflate(R.layout.fragment_market, container, false)
        val btn: Button = view.find(R.id.searchButtonId)
        btn.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        println("Button clicked")
        var stockText: EditText = (v?.findViewById(R.id.textStockId) as EditText)
        println(stockText.text)
    }
}