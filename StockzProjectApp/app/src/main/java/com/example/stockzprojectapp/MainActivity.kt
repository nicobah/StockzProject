package com.example.stockzprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), MarketAdapter.OnItemClickListener {
    private val dummylist = generateDummyList()
    private val adapter = MarketAdapter(dummylist, this)


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


            val recyclerView: RecyclerView = findViewById(R.id.market_rv)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            //setHasFixedSize is not neccesarry
            recyclerView.setHasFixedSize(true)
        }






    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem: DummyItem = dummylist[position]
        clickedItem.name = "clicked"
        adapter.notifyDataSetChanged()

    }

    private fun generateDummyList(): List<DummyItem> {
        val list = ArrayList<DummyItem>()
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))
        list.add(DummyItem("ItemNo1", 1))
        list.add(DummyItem("ItemNo5", 5))
        list.add(DummyItem("ItemNo10", 10))
        list.add(DummyItem("ItemNo100", 100))
        list.add(DummyItem("ItemNoGameStop", 999999999))

        return list
    }


}