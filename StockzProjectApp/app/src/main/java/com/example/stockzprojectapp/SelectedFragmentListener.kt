package com.example.stockzprojectapp

import androidx.fragment.app.Fragment

interface SelectedFragmentListener {

    fun getSelectedFragment(): Fragment {
        return NotImplementedError() as Fragment
    }

    fun setSelectedFragment(fragment: Fragment){
    }
}