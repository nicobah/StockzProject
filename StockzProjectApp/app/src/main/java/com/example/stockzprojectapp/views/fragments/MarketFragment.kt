package com.example.stockzprojectapp.views.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.stockzprojectapp.DatabaseHelper
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.databinding.FragmentMarketBinding
import com.example.stockzprojectapp.models.Stock
import com.example.stockzprojectapp.models.StockService
import com.example.stockzprojectapp.viewmodels.PortfolioViewModel
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL

class MarketFragment : Fragment(R.layout.fragment_market), View.OnClickListener {

    private val portfolioViewModel: PortfolioViewModel by activityViewModels()
    lateinit var dateKey: String
    lateinit var json: JSONObject
    lateinit var myStock: Stock
    private lateinit var binding: FragmentMarketBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var stockService: StockService

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("entered_stock", binding.textStockId.text.toString())
        outState.putBoolean("isVisible", binding.stockResultLabel.isVisible)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMarketBinding.bind(view)
        binding.searchButtonId.setOnClickListener(this)
        binding.portfolioButtonId.setOnClickListener(this)
        stockService = StockService()

        if (savedInstanceState != null && savedInstanceState.getBoolean("isVisible")) {
            setElementsVisibility(true)
            binding.portfolioButtonId.isEnabled = true
        } else {
            setElementsVisibility(false)
            binding.portfolioButtonId.isEnabled = false
            //execute(savedInstanceState.getString("entered_stock", ""))
        }
        dbHelper = DatabaseHelper(requireActivity().applicationContext)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.searchButtonId -> {
                println("Stock: " + binding.textStockId.text.toString())
                val stockPrice = execute(binding.textStockId.text.toString())
                v.hideKeyboard()
                setElementsVisibility(true)
            }
            R.id.portfolioButtonId -> {
                myStock.addAmount(binding.numberOfStockId.text.toString().toInt())
                lifecycleScope.launch { saveStock(myStock) }
                v.hideKeyboard()
            }
            else -> {
            }
        }
    }

    private fun saveStock(myStock: Stock) {
        val db = dbHelper.readableDatabase
        dbHelper.addStocks(db, myStock)
        portfolioViewModel.addStock(myStock)
        Toast.makeText(
            activity,
            "${myStock.amount} stocks of ${myStock.symbol} added to your portfolio!",
            Toast.LENGTH_LONG
        ).show()
        db.close()
    }

    fun execute(symbol: String) {
        stockService.execute(symbol)
        if (stockService.price == -1.0f) {
            binding.textResultId.setText("Stock not found")
            binding.numberOfStockId.isEnabled = false
            binding.portfolioButtonId.isEnabled = false
        } else {
            myStock = Stock(
                symbol,
                stockService.price,
                stockService.dateKey
            )
            var myString = "${myStock.date}\n$${myStock.price}"
            binding.textResultId.setText(myString)
            binding.numberOfStockId.setText("0")
            binding.numberOfStockId.isEnabled = true
            binding.portfolioButtonId.isEnabled = true
        }
    }

    /*fun execute(symbol: String) {
        val url =
            "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=5min&apikey=KX1X7LVV24I06XJE".format(
                symbol
            )
        // send the URL request and read the response as a asyncrone call
        val fetch = doAsync {
            val result = URL(url).readText()
            json = JSONObject(result)
            dateKey = ((json["Meta Data"] as JSONObject).getString("3. Last Refreshed"))
            var price = getPrice(result)

            if (price == -1.0f) {
                binding.textResultId.setText("Stock not found")
                binding.numberOfStockId.isEnabled = false
                binding.portfolioButtonId.isEnabled = false
            } else {
                uiThread {
                    myStock = Stock(
                        symbol,
                        price,
                        dateKey
                    )
                    var myString = "${myStock.date}\n$${myStock.price}"
                    binding.textResultId.setText(myString)
                    binding.numberOfStockId.setText("0")
                    binding.numberOfStockId.isEnabled = true
                    binding.portfolioButtonId.isEnabled = true
                }
            }

        }
    }*/

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onDestroy() {
        dbHelper.close()
        super.onDestroy()
    }

    private fun setElementsVisibility(visible: Boolean) {
        binding.portfolioButtonId.isVisible = visible
        binding.stockResultLabel.isVisible = visible
        binding.stockAmountLabel.isVisible = visible
        binding.stockGraphLabel.isVisible = visible
        binding.numberOfStockId.isVisible = visible
        binding.stockGraphImage.isVisible = visible
    }
}