package com.example.stockzprojectapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockzprojectapp.databinding.FragmentPortfolioBinding


class PortfolioFragment : Fragment(R.layout.fragment_portfolio) {
    private val dummylist = generateDummyList()
    private val marketAdapter = MarketAdapter(dummylist)
    private lateinit var binding: FragmentPortfolioBinding

    private var listener: OnItemClickListener? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPortfolioBinding.bind(view)
        binding.marketRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = marketAdapter
        }
    }


    fun onListItemClick(position: Int) {
        //Toast.makeText(context, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem: DummyItem = dummylist[position]
        clickedItem.name = "clicked"
        marketAdapter.notifyDataSetChanged()

        listener?.onItemClick(position)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception.
        listener = try {
            activity as OnItemClickListener?
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString()
                    + " must implement OnItemClickListener")
        }
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

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}