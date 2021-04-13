package com.example.stockzprojectapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.stockzprojectapp.viewmodels.PortfolioViewModel
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.models.DummyItem

class PortfolioDetailedFragment : Fragment() {

    private val portfolioViewModel: PortfolioViewModel by activityViewModels()

    private lateinit var stock: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.portfolio_detailed_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stock = view.findViewById(R.id.testText)

        val portfolioObserver = Observer<Pair<Int, DummyItem>>{
            stock.text = "${it.second.name} is currently valued at ${it.second.value}"
        }

        portfolioViewModel.getSelectedStock().observe(viewLifecycleOwner, portfolioObserver)
    }

}