package com.example.stockzprojectapp.views.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.stockzprojectapp.DatabaseHelper
import com.example.stockzprojectapp.viewmodels.PortfolioViewModel
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.SelectedFragmentListener
import com.example.stockzprojectapp.models.Stock
import com.example.stockzprojectapp.databinding.FragmentPortfolioBinding
import com.example.stockzprojectapp.models.Portfolio
import com.example.stockzprojectapp.views.MarketAdapter
import kotlinx.coroutines.launch
import java.lang.ClassCastException
import kotlin.collections.ArrayList
import kotlin.random.Random


class PortfolioFragment : Fragment(R.layout.fragment_portfolio){
    private val portfolioViewModel: PortfolioViewModel by activityViewModels()
    private lateinit var stocklist: ArrayList<Stock>
    private lateinit var marketAdapter: MarketAdapter
    private lateinit var binding: FragmentPortfolioBinding
    private lateinit var portfolio: Portfolio
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var listener: SelectedFragmentListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPortfolioBinding.bind(view)

        dbHelper = DatabaseHelper(requireActivity().applicationContext)

        lifecycleScope.launch {
            loadViewModel()
            loadPortfolio()

            marketAdapter = MarketAdapter(stocklist)

            binding.marketRv.apply {
                adapter = marketAdapter
            }

            marketAdapter.onItemClick =  {
                val newFragment = PortfolioDetailedFragment(it.symbol)
                listener.setSelectedFragment(newFragment)
                parentFragmentManager.beginTransaction().replace(R.id.fragment_container, newFragment).addToBackStack("").commit()
            }
        }
    }

    fun loadViewModel(){
        portfolioViewModel.clearStocks()

        val db = dbHelper!!.readableDatabase
        val rs = db.rawQuery("SELECT * FROM STOCKS", null)

        while(rs.moveToNext()){
            portfolioViewModel.addStock(
                Stock(
                    symbol = rs.getString(1),
                    price = rs.getFloat(2),
                    date = "date",
                    amount = rs.getInt(3)))
        }

        stocklist = portfolioViewModel.getStocks()
        rs.close()
        db.close()
    }

    fun loadPortfolio(){
        portfolio = Portfolio()

        var totalValue = 0.0

        for(stock in stocklist){
            totalValue += stock.price * stock.amount
        }

        portfolio.setTotalValue(totalValue)
        portfolio.setStats(14.6)

        binding.portfolio = portfolio

        var colorStats = if(portfolio.getStats() > 0) Color.GREEN else Color.RED
        var colorValue = if(portfolio.getTotalValue() > 0) Color.GREEN else Color.RED

        binding.textPortfolioStats.setTextColor(colorStats)
        binding.textPortfolioTotalValue.setTextColor(colorValue)
    }

    override fun onDestroy() {
        if(this::dbHelper.isInitialized) dbHelper.close()
        super.onDestroy()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as SelectedFragmentListener
        } catch (ex: ClassCastException){
            //Activity does not implement listener
        }
    }
}