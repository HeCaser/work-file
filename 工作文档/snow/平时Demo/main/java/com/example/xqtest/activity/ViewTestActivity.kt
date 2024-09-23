package com.example.xqtest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.xqtest.MainActivity
import com.example.xqtest.R
import com.example.xqtest.util.ActivityUtils
import kotlinx.android.synthetic.main.activity_view_test.*

class ViewTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_private_detail)
//        tvTest.setOnClickListener {
//        }
//
//        tvTest2.setOnClickListener {
//        }
//        println("hepan ViewTestActivity 创建 ${this.hashCode()}")
        println("hepan 创建")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        println("hepan ViewTestActivity onNewIntent ${this.hashCode()}")

    }

    override fun onPause() {
        super.onPause()
        println("hepan onpause")
    }

    /**
     * 调用此方法
     */
    fun goBack(v:View){
        ActivityUtils.convertActivityToTranslucent(this,object :ActivityUtils.TranslucentListener{
            override fun onTranslucent() {
            }
        })
    }
    override fun onRestart() {
        super.onRestart()
        println("hepan onRestart")
    }
}