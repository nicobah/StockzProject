package com.example.stockzprojectapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.databinding.FragmentInspirationBinding
import com.example.stockzprojectapp.models.Repository
import com.example.stockzprojectapp.viewmodels.InspirationViewModel
import com.example.stockzprojectapp.viewmodels.InspirationViewModelFactory
import com.example.stockzprojectapp.views.InspirationAdapter

class InspirationFragment : Fragment(), InspirationAdapter.ViewHolderListener {
    private lateinit var viewModel: InspirationViewModel
    private lateinit var binding: FragmentInspirationBinding
    private lateinit var inspirationAdapter: InspirationAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_inspiration, container, false)
        binding = FragmentInspirationBinding.bind(view)
        val repository = Repository()
        val viewModelFactory = InspirationViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(InspirationViewModel::class.java)
        viewModel.getPopularLists()
        inspirationAdapter = InspirationAdapter(this)
        inspirationAdapter.setStocks(viewModel.getStocks().value!!)
        binding.inspirationRv.apply {
            adapter = inspirationAdapter
        }

        viewModel.getStocks().observe(viewLifecycleOwner, Observer { array ->
            inspirationAdapter.setStocks(array)
        })
        viewModel.getPortfolioName().observe(viewLifecycleOwner, Observer { name ->
            binding.listNameText.text = name
        })
        binding.refreshButton.text = "Click for a new random portfolio"

        return view
    }

    override fun selectStock(position: Int) {
        Toast.makeText(activity, "View $position Clicked", Toast.LENGTH_SHORT).show()
    }

}