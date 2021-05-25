package com.example.stockzprojectapp.views

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.stockzprojectapp.R
import com.example.stockzprojectapp.SelectedFragmentListener
import com.example.stockzprojectapp.databinding.ActivityMainBinding
import com.example.stockzprojectapp.viewmodels.PortfolioViewModel
import com.example.stockzprojectapp.views.fragments.InspirationFragment
import com.example.stockzprojectapp.views.fragments.MarketFragment
import com.example.stockzprojectapp.views.fragments.PortfolioDetailedFragment
import com.example.stockzprojectapp.views.fragments.PortfolioFragment
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity(), SelectedFragmentListener {

    private val portfolioViewModel: PortfolioViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var currentFragment: Fragment
    private var restored: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sleep(2000)
        setTheme(R.style.Theme_StockzProjectApp)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        currentFragment = if (savedInstanceState == null) {
            PortfolioFragment()
        } else {
            supportFragmentManager.getFragment(savedInstanceState, "fragment")!!
        }

        Log.d("Selected Fragment", currentFragment.toString())

        restored = (savedInstanceState == null)

        when(currentFragment){
            is PortfolioFragment -> {
                Log.d("Loaded Fragment", "Portfolio fragment")
            }
            is PortfolioDetailedFragment -> {
                Log.d("Loaded Fragment", "Portfolio detailed fragment")
            }
            is MarketFragment -> {
                Log.d("Loaded Fragment", "Market fragment")
            }
            is InspirationFragment -> {
                Log.d("Loaded Fragment", "Inspiration fragment")
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, currentFragment).commit()

        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.portfolio -> {
                    currentFragment =
                        PortfolioFragment()
                }
                R.id.search -> {
                    currentFragment =
                        MarketFragment()
                }
                R.id.settings -> {
                    currentFragment =
                        InspirationFragment()
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, currentFragment).commit()
            //returns true for selected menu item
            return@setOnNavigationItemSelectedListener true
        }

        /*val portfolioObserver = Observer<Pair<Int, Stock>> {
            val newFragment = PortfolioDetailedFragment()

            val transaction = supportFragmentManager.beginTransaction()

            transaction.replace(R.id.fragment_container, newFragment).addToBackStack("")

            selectedFragment = newFragment

            transaction.commit()
        }
        portfolioViewModel.getSelectedStock().observe(this, portfolioObserver)*/
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        when(currentFragment){
            is PortfolioFragment -> {
                Log.d("Saved Fragment", "Portfolio fragment")
            }
            is PortfolioDetailedFragment -> {
                Log.d("Saved Fragment", "Portfolio detailed fragment")
            }
            is MarketFragment -> {
                Log.d("Saved Fragment", "Market fragment")
            }
            is InspirationFragment -> {
                Log.d("Saved Fragment", "Inspiration fragment")
            }
        }
        supportFragmentManager.putFragment(outState, "fragment", currentFragment)
    }

    override fun setSelectedFragment(fragment: Fragment) {
        currentFragment = fragment
    }

    override fun getSelectedFragment(): Fragment {
        return currentFragment
    }
}