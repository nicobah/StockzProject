package com.example.stockzprojectapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class PortfolioFragment : Fragment(), MarketAdapter.OnItemClickListener {
    private val dummylist = generateDummyList()
    private val adapter = MarketAdapter(dummylist, this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_portfolio, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var recyclerView: RecyclerView = view.findViewById(R.id.market_rv)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }




    override fun onItemClick(position: Int) {
        Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem: DummyItem = dummylist[position]
        clickedItem.name = "clicked"
        adapter.notifyDataSetChanged()

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