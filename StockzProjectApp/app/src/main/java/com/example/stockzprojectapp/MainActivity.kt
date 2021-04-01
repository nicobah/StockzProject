package com.example.stockzprojectapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.stockzprojectapp.databinding.ActivityMainBinding
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity(), PortfolioFragment.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var selectedFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sleep(2000)
        setTheme(R.style.Theme_StockzProjectApp)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        selectedFragment = PortfolioFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.portfolio -> {
                    selectedFragment = PortfolioFragment()
                }
                R.id.search -> {
                    selectedFragment = MarketFragment()
                }
                R.id.random -> {
                    selectedFragment = RandomFragment()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            //returns true for selected menu item
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
    }
}