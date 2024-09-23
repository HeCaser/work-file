package com.example.xqtest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xqtest.R
import kotlinx.android.synthetic.main.activity_chnage_layout_test.*

class ChangeLayoutTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chnage_layout_test)

        tvTest1.setOnClickListener {
            llFixView.changeLayout()
        }

        tvTest2.setOnClickListener {
            llFixView.requestLayout()
        }
    }
}