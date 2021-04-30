package com.example.stockzprojectapp.views

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stockzprojectapp.databinding.InspirationRvItemBinding
import com.example.stockzprojectapp.databinding.MarketRvItemBinding
import com.example.stockzprojectapp.models.Stock

class InspirationAdapter : RecyclerView.Adapter<InspirationAdapter.ViewHolder> {


    private lateinit var marketList: ArrayList<Stock>
    private val listener: ViewHolderListener

    constructor(listener: ViewHolderListener) : super() {
        this.listener = listener
    }

    fun setStocks(stockList: ArrayList<Stock>){
        marketList = stockList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vHolder =
            ViewHolder(
                InspirationRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        vHolder.itemView.setOnClickListener { _ -> selectStock(vHolder.adapterPosition) }
        return vHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = marketList[position]
        holder.name.text = currentItem.symbol
        holder.price.text = currentItem.price.toString()
    }

    override fun getItemCount() = marketList.size

    fun selectStock(position: Int){
        listener.selectStock(position)
    }

    class ViewHolder(private val binding: InspirationRvItemBinding) : RecyclerView.ViewHolder(binding.root){
        val name: TextView = binding.inspirationName
        val price: TextView = binding.inspirationPrice

    }

    interface ViewHolderListener {
        fun selectStock(position: Int)
    }
}