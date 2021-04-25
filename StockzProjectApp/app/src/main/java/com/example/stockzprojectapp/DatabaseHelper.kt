package com.example.stockzprojectapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.stockzprojectapp.models.Stock

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "STOCKDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE STOCKS(STOCKID INTEGER PRIMARY KEY AUTOINCREMENT, SYMBOL TEXT, AMOUNT INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun clearDB(db: SQLiteDatabase?){
        db?.execSQL("DELETE FROM STOCKS")
    }

    fun addStocks(db: SQLiteDatabase?, stock: Stock){
        db?.execSQL("INSERT INTO STOCKS(SYMBOL, AMOUNT) VALUES('${stock.symbol}', ${stock.amount})")
    }
}