package com.example.stockzprojectapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MarketAdapter(private val marketList: List<DummyItem>) :
    RecyclerView.Adapter<MarketAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.market_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = marketList[position]
        holder.marketText.text = currentItem.name
        holder.marketValue.text = currentItem.value.toString()


    }

    override fun getItemCount() = marketList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val marketText: TextView = itemView.findViewById(R.id.market_item_name)
        val marketValue: TextView = itemView.findViewById(R.id.market_item_value)

    }
}