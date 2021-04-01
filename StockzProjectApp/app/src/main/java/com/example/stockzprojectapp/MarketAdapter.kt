package com.example.stockzprojectapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stockzprojectapp.databinding.MarketRvItemBinding

class MarketAdapter(private val marketList: List<DummyItem>) : RecyclerView.Adapter<MarketAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MarketRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = marketList[position]
        holder.marketText.text = currentItem.name
        holder.marketValue.text = currentItem.value.toString()
    }

    override fun getItemCount() = marketList.size

    inner class ViewHolder(private val binding: MarketRvItemBinding) : RecyclerView.ViewHolder(binding.root),  View.OnClickListener{
        val marketText: TextView = binding.marketItemName
        val marketValue: TextView = binding.marketItemValue

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if(position != RecyclerView.NO_POSITION){
                //TO-DO Add on click functionality to send info to fragment
            }
        }
    }
}