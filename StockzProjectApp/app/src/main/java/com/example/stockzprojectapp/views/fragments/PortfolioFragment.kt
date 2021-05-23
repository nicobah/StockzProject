package com.example.stockzprojectapp.views.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.stockzprojectapp.DatabaseHelper
import com.example.stockzprojectapp.viewmodels.PortfolioViewModel
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.models.Stock
import com.example.stockzprojectapp.databinding.FragmentPortfolioBinding
import com.example.stockzprojectapp.models.Portfolio
import com.example.stockzprojectapp.views.MarketAdapter
import java.util.*
import kotlin.collections.ArrayList


class PortfolioFragment : Fragment(R.layout.fragment_portfolio),
    MarketAdapter.ViewHolderListener {
    private val portfolioViewModel: PortfolioViewModel by activityViewModels()
    private lateinit var stocklist: ArrayList<Stock>
    private lateinit var marketAdapter: MarketAdapter
    private lateinit var binding: FragmentPortfolioBinding
    private lateinit var portfolio: Portfolio


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPortfolioBinding.bind(view)

        loadViewModel()
        loadPortfolio()

        marketAdapter =
            MarketAdapter(this, stocklist)
        binding.marketRv.apply {
            adapter = marketAdapter
        }
    }

    override fun selectStock(position: Int) {
        Toast.makeText(activity, "View $position Clicked", Toast.LENGTH_SHORT).show()
        portfolioViewModel.selectStockAt(position)
    }

    fun loadViewModel(){
        portfolioViewModel.clearStocks()

        val helper = DatabaseHelper(requireActivity().applicationContext)
        val db = helper.readableDatabase
        val rs = db.rawQuery("SELECT * FROM STOCKS", null)

        while(rs.moveToNext()){
            portfolioViewModel.addStock(
                Stock(
                    symbol = rs.getString(1),
                    price = 12.0f,
                    date = "date",
                    amount = rs.getInt(2)))
        }

        stocklist = portfolioViewModel.getStocks()
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



    //Should probably add to viewmodel instead
//    public fun addToPortfolio(stock: Stock){
//        stocklist.add(stock)
//        stocklist.forEach {
//            println(it.symbol)
//            println(it.date)
//            println(it.price)
//            println(it.amount)
//        }
//        marketAdapter.updateAdapter(stocklist)
//    }
}