package com.example.xqtest.parcelable

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xqtest.R

class ParcelableTestActivity : AppCompatActivity() {

    companion object{
        val KEY = "extra"
        val BYTE_LENGTH = 1024
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable_test)

        val list = intent.getParcelableArrayListExtra<StockQuote>("extra")

        println("hepan 接收长度 ${list?.size} 第一个 market byte0 = ${list?.get(0)?.market?.bArray?.get(0)}")
    }
}