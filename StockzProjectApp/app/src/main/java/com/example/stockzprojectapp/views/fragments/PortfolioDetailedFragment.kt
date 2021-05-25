package com.example.stockzprojectapp.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stockzprojectapp.viewmodels.PortfolioViewModel
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.databinding.FragmentDetailedPortfolioBinding
import com.example.stockzprojectapp.models.Stock
import com.example.stockzprojectapp.viewmodels.DetailedViewModel
import com.example.stockzprojectapp.viewmodels.InspirationViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class PortfolioDetailedFragment(symbol: String = "") :
    Fragment(R.layout.fragment_detailed_portfolio) {

    private lateinit var viewModel: DetailedViewModel
    private var symbol: String = symbol
    private lateinit var stockValue: TextView
    private lateinit var todayRise: TextView
    private lateinit var todayHigh: TextView
    private lateinit var todayLow: TextView
    private lateinit var openingPrice: TextView
    private lateinit var binding: FragmentDetailedPortfolioBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(DetailedViewModel::class.java)
        viewModel.execute(symbol)

        binding = FragmentDetailedPortfolioBinding.bind(view)

        stockValue = binding.stockPrice
        todayRise = binding.todayRise
        todayHigh = binding.todayHigh
        todayLow = binding.todayLow
        openingPrice = binding.openingPrice

        viewModel.getStockPrice().observe(viewLifecycleOwner, Observer {
            binding.stockPrice.setText(it)
        })
        viewModel.getPercentRise().observe(viewLifecycleOwner, Observer {
            binding.todayRise.setText(it.toString())
        })
        viewModel.getTodayHigh().observe(viewLifecycleOwner, Observer {
            binding.todayHigh.text = "Todays high: $it"
        })
        viewModel.getTodayLow().observe(viewLifecycleOwner, Observer {
            binding.todayLow.text = "Todays low: $it"
        })
        viewModel.getOpeningPrice().observe(viewLifecycleOwner, Observer {
            binding.openingPrice.text = "Opening price: $it"
        })

        binding.stockSymbol.setText("$symbol")
    }


}