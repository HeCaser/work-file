package com.example.xqtest.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.xqtest.R

class TraceActivity : AppCompatActivity() {

    companion object{
        fun start(ctx:Context){
            ctx.startActivity(Intent(ctx,TraceActivity::class.java))
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}