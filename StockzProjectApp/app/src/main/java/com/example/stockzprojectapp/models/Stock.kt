package com.example.stockzprojectapp.models

import android.os.Parcel
import android.os.Parcelable

class Stock(symbol: String, price: Float, date: String, amount: Int = 0, longName: String = "None", peRatio: Float = 0F): Parcelable {
    var symbol: String = symbol
    var price: Float = price
    var date: String = date
    var amount: Int = amount
    var longName = longName
    var peRatio = peRatio

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        price = parcel.readFloat(),
        date = parcel.readString()!!,
        amount = parcel.readInt(),
        longName = parcel.readString()!!,
        peRatio = parcel.readFloat()
    ) {
    }

    fun addAmount(amount: Int) {
        this.amount += amount
    }

    fun addLongName(longName: String) {
        this.longName = longName
    }

    fun addPE(pe: Float) {
        this.peRatio = pe
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(symbol)
        parcel.writeFloat(price)
        parcel.writeString(date)
        parcel.writeInt(amount)
        parcel.writeString(longName)
        parcel.writeFloat(peRatio)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Stock> {
        override fun createFromParcel(parcel: Parcel): Stock {
            return Stock(parcel)
        }

        override fun newArray(size: Int): Array<Stock?> {
            return arrayOfNulls(size)
        }
    }
}