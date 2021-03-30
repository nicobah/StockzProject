package com.example.stockzprojectapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockzprojectapp.databinding.FragmentPortfolioBinding


class PortfolioFragment : Fragment(R.layout.fragment_portfolio), MarketAdapter.OnItemClickListener {
    private val dummylist = generateDummyList()
    private val marketAdapter = MarketAdapter(dummylist, this)
    private lateinit var binding: FragmentPortfolioBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPortfolioBinding.bind(view)
        binding.marketRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = marketAdapter
        }
    }


    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem: DummyItem = dummylist[position]
        clickedItem.name = "clicked"
        marketAdapter.notifyDataSetChanged()

    }

    private fun generateDummyList(): List<DummyItem> {
        val list = ArrayList<DummyItem>()
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))

        return list
    }

}