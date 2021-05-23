package com.example.stockzprojectapp.views

import android.app.Activity
import android.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.stockzprojectapp.R

class ProgressBar(val activity: Activity?) {
    private lateinit var dialog: AlertDialog

    fun startLoading() {

        val inflater = activity?.layoutInflater
        val dialogView = inflater?.inflate(R.layout.loading_bar, null)
        val builder = AlertDialog.Builder(activity)
        builder.setView(dialogView)
        builder.setCancelable(false)
        dialog = builder.create()
        dialog.show()

    }
    fun dismiss(){
        if(this::dialog.isInitialized) {
            dialog.dismiss()
        }
    }

}