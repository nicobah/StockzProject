package com.example.stockzprojectapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.databinding.FragmentInspirationBinding
import com.example.stockzprojectapp.models.Repository
import com.example.stockzprojectapp.viewmodels.InspirationViewModel
import com.example.stockzprojectapp.viewmodels.InspirationViewModelFactory

class InspirationFragment : Fragment() {
    private lateinit var viewModel: InspirationViewModel
    private lateinit var binding: FragmentInspirationBinding


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
        return view
    }

}