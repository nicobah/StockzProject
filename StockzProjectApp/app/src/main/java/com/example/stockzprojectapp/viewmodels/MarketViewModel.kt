package com.example.stockzprojectapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockzprojectapp.models.Repository
import kotlinx.coroutines.launch

class MarketViewModel(private val repository: Repository): ViewModel() {
    val myRes: MutableLiveData<String> = MutableLiveData()

    fun getPopularLists(){
        viewModelScope.launch {
            val res = repository.getPopularLists()
            Log.d("TAG", res.toString())
        }
    }

}