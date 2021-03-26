package com.example.stockzprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bnv: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bnv.setOnNavigationItemSelectedListener {
            lateinit var selectedFragment: Fragment
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
            return@setOnNavigationItemSelectedListener true
        }

        }









}