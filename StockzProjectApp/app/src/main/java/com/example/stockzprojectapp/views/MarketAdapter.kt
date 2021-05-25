package com.example.stockzprojectapp.views

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.stockzprojectapp.models.Stock
import com.example.stockzprojectapp.databinding.MarketRvItemBinding

class MarketAdapter : RecyclerView.Adapter<MarketAdapter.ViewHolder> {

    private var marketList: List<Stock>
    var onItemClick: ((Stock) -> Unit)? = null

    constructor(stocks: List<Stock>) : super() {
        this.marketList = stocks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vHolder =
            ViewHolder(
                MarketRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        return vHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = marketList[position]
        val totalValue = currentItem.price * currentItem.amount
        holder.portfolioItemSymbol.text = currentItem.symbol
        holder.portfolioItemValue.text = "$%.2f".format(currentItem.price)
        holder.portfolioItemAmount.text = currentItem.amount.toString()
        holder.portfolioItemTotalValue.text = "$%.2f".format(totalValue)
    }

    override fun getItemCount() = marketList.size

    inner class ViewHolder(private val binding: MarketRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val portfolioItemSymbol: TextView = binding.portfolioItemSymbol
        val portfolioItemValue: TextView = binding.portfolioItemValue
        val portfolioItemAmount: TextView = binding.portfolioItemAmount
        val portfolioItemTotalValue: TextView = binding.portfolioItemTotalvalue

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(marketList[adapterPosition])
            }

        }
    }
}