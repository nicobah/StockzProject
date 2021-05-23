package com.example.stockzprojectapp.views.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.databinding.FragmentInspirationBinding
import com.example.stockzprojectapp.models.Repository
import com.example.stockzprojectapp.viewmodels.InspirationViewModel
import com.example.stockzprojectapp.viewmodels.InspirationViewModelFactory
import com.example.stockzprojectapp.views.InspirationAdapter
import com.example.stockzprojectapp.views.ProgressBar
import org.jetbrains.anko.sdk27.coroutines.onClick

class InspirationFragment : Fragment(), InspirationAdapter.ViewHolderListener {
    private lateinit var viewModel: InspirationViewModel
    private lateinit var binding: FragmentInspirationBinding
    private lateinit var inspirationAdapter: InspirationAdapter
    private lateinit var progressBar: ProgressBar


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_inspiration, container, false)
        binding = FragmentInspirationBinding.bind(view)
        val repository = Repository()
        val viewModelFactory = InspirationViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(InspirationViewModel::class.java)
        inspirationAdapter = InspirationAdapter(this)
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            requireContext().resources.getDrawable(
                R.drawable.rec_divider,
                requireContext().theme
            )
        )
        if (savedInstanceState == null) {
            viewModel.getPopularListsResponseAndSetWatchList()
        }

        inspirationAdapter.setStocks(viewModel.getStocks().value!!)
        binding.inspirationRv.apply {
            adapter = inspirationAdapter
            addItemDecoration(divider)
        }

        progressBar = ProgressBar(activity)
        viewModel.getIsLoading().observe(viewLifecycleOwner, Observer { b ->
            if (b == true) {
                progressBar.startLoading()
            } else if (b == false) {
                progressBar.dismiss()
            }
        })

        viewModel.getStocks().observe(viewLifecycleOwner, Observer { array ->
            inspirationAdapter.setStocks(array)
        })
        viewModel.getPortfolioName().observe(viewLifecycleOwner, Observer { name ->
            binding.listNameText.text = name
        })
        binding.refreshButton.onClick {
            viewModel.setWatchList()
        }

        return view
    }

    override fun selectStock(position: Int) {
        Toast.makeText(activity, "View $position Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        viewModel.getIsLoading().observe(viewLifecycleOwner, Observer { b ->
            if (b == true) {
                progressBar.dismiss()
            }
        })

    }

}