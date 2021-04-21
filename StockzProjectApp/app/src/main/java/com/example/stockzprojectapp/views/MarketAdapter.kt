package com.example.stockzprojectapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockzprojectapp.models.Stock
import com.example.stockzprojectapp.databinding.MarketRvItemBinding

class MarketAdapter : RecyclerView.Adapter<MarketAdapter.ViewHolder> {

    private var marketList: List<Stock>
    private val listener: ViewHolderListener

    constructor(listener: ViewHolderListener, stocks: List<Stock>) : super() {
        this.marketList = stocks
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vHolder =
            ViewHolder(
                MarketRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        vHolder.itemView.setOnClickListener { _ -> selectStock(vHolder.adapterPosition) }
        return vHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = marketList[position]
        holder.marketText.text = currentItem.symbol
        holder.marketValue.text = currentItem.price.toString()
    }

    override fun getItemCount() = marketList.size

    fun selectStock(position: Int){
        listener.selectStock(position)
    }

    class ViewHolder(private val binding: MarketRvItemBinding) : RecyclerView.ViewHolder(binding.root){
        val marketText: TextView = binding.marketItemName
        val marketValue: TextView = binding.marketItemValue

    }

    interface ViewHolderListener {
        fun selectStock(position: Int)
    }
}