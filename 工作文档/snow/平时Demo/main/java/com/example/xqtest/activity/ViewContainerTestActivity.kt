package com.example.xqtest.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.example.xqtest.R

class ViewContainerTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_container_test)

        val cv2 = findViewById<ViewGroup>(R.id.cv2)
        for (i in 0 until cv2.childCount){
            println("hepan ${cv2.get(i)}")
        }
    }
}