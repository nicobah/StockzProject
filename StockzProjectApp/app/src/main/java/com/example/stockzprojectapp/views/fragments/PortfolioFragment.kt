package com.example.stockzprojectapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.stockzprojectapp.viewmodels.PortfolioViewModel
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.databinding.FragmentPortfolioBinding
import com.example.stockzprojectapp.models.DummyItem
import com.example.stockzprojectapp.views.MarketAdapter


class PortfolioFragment : Fragment(R.layout.fragment_portfolio),
    MarketAdapter.ViewHolderListener {
    private val portfolioViewModel: PortfolioViewModel by activityViewModels()
    private lateinit var dummyList: ArrayList<DummyItem>
    private lateinit var marketAdapter: MarketAdapter
    private lateinit var binding: FragmentPortfolioBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dummyList = portfolioViewModel.getStocks()
        marketAdapter =
            MarketAdapter(this, dummyList)
        binding = FragmentPortfolioBinding.bind(view)
        binding.marketRv.apply {
            adapter = marketAdapter
        }
    }

    override fun selectStock(position: Int) {
        Toast.makeText(activity, "View $position Clicked", Toast.LENGTH_SHORT).show()
        portfolioViewModel.selectStockAt(position)
    }
}