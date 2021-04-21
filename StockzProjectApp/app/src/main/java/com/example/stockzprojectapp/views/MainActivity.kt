package com.example.stockzprojectapp.views

import androidx.activity.viewModels
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.stockzprojectapp.*
import com.example.stockzprojectapp.databinding.ActivityMainBinding
import com.example.stockzprojectapp.models.Stock
import com.example.stockzprojectapp.viewmodels.PortfolioViewModel
import com.example.stockzprojectapp.views.fragments.MarketFragment
import com.example.stockzprojectapp.views.fragments.PortfolioDetailedFragment
import com.example.stockzprojectapp.views.fragments.PortfolioFragment
import com.example.stockzprojectapp.views.fragments.SettingsFragment
import java.lang.Thread.sleep



class MainActivity : AppCompatActivity() {

    private val portfolioViewModel: PortfolioViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    lateinit var selectedFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sleep(2000)
        setTheme(R.style.Theme_StockzProjectApp)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        selectedFragment =
            PortfolioFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.portfolio -> {
                    selectedFragment =
                        PortfolioFragment()
                }
                R.id.search -> {
                    selectedFragment =
                        MarketFragment()
                }
                R.id.settings -> {
                    selectedFragment =
                        SettingsFragment()
                }
            }
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, selectedFragment).commit()
            //returns true for selected menu item
            return@setOnNavigationItemSelectedListener true
        }

        val portfolioObserver = Observer<Pair<Int, Stock>>{
            val newFragment =
                PortfolioDetailedFragment()
            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fragment_container, newFragment).addToBackStack(null)

            transaction.commit()
        }

        portfolioViewModel.getSelectedStock().observe(this, portfolioObserver)
    }
}